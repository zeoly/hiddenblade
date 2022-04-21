package com.yahacode.hiddenblade.app.cache;

import com.yahacode.hiddenblade.app.spi.RedisPasswordAdapter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * redis cache configuration
 *
 * @author zengyongli
 * @since 2022/04/12
 */
@EnableCaching
@Configuration
@ConditionalOnBean(RedisProperties.class)
public class RedisConfig {

    private static Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    RedisProperties redisProperties;

    @Bean
    CacheManager redisCacheManager(LettuceConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = buildConfigWithTimeout(Duration.ofSeconds(redisProperties.getDefaultTimeout()));
        log.info("hidden blade redis cache manager init ...");
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory).cacheDefaults(configuration).transactionAware();
        if (redisProperties.getCustomTimeout() != null) {
            redisCacheManagerBuilder.withInitialCacheConfigurations(customConfiguration(redisProperties.getCustomTimeout()));
        }
        return redisCacheManagerBuilder.build();
    }

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        if (redisProperties.getMode() == RedisMode.CLUSTER) {
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getNodes());
            configuration.setPassword(getPassword());
            log.info("hidden blade redis cluster init ...");
            return new LettuceConnectionFactory(configuration);
        } else {
            String[] node = redisProperties.getNodes().get(0).split(":");
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(node[0], Integer.parseInt(node[1]));
            configuration.setPassword(getPassword());
            log.info("hidden blade redis standalone init ...");
            return new LettuceConnectionFactory(configuration);
        }
    }

    private String getPassword() {
        ServiceLoader<RedisPasswordAdapter> adapters = ServiceLoader.load(RedisPasswordAdapter.class);
        Iterator<RedisPasswordAdapter> iterator = adapters.iterator();
        if (iterator.hasNext()) {
            RedisPasswordAdapter adapter = iterator.next();
            log.info("get password from {}", adapter.getClass().getSimpleName());
            return adapter.get();
        } else {
            return redisProperties.getPassword();
        }
    }

    private LettuceClientConfiguration getClientConfiguration() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getMinIdle());
        poolConfig.setMaxTotal(redisProperties.getMaxActive());
        poolConfig.setMaxWait(Duration.ofSeconds(redisProperties.getMaxWait()));
        return LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(redisProperties.getCommandTimeout()))
                .shutdownTimeout(Duration.ofMillis(redisProperties.getShutdownTimeout())).poolConfig(poolConfig).build();
    }

    private RedisCacheConfiguration buildConfigWithTimeout(Duration duration) {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(duration)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues().computePrefixWith(name -> name + ":");
    }

    private Map<String, RedisCacheConfiguration> customConfiguration(Map<String, Integer> configs) {
        Map<String, RedisCacheConfiguration> customConfig = new HashMap<>();
        for (String key : configs.keySet()) {
            customConfig.put(key, buildConfigWithTimeout(Duration.ofSeconds(configs.get(key))));
        }
        return customConfig;
    }
}
