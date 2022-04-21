package com.yahacode.hiddenblade.app.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

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
    CacheManager redisCacheManager(LettuceConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(redisProperties.getDefaultTimeout())).computePrefixWith(name -> name + ":")
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();
        log.info("hidden blade redis cache manager init ...");
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(configuration).transactionAware().build();
    }

    @Bean
    LettuceConnectionFactory connectionFactory() {
        if (redisProperties.getMode() == RedisMode.CLUSTER) {
            RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getNodes());
            configuration.setPassword(redisProperties.getPassword());
            log.info("hidden blade redis cluster init ...");
            return new LettuceConnectionFactory(configuration);
        } else {
            String[] node = redisProperties.getNodes().get(0).split(":");
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(node[0], Integer.parseInt(node[1]));
            configuration.setPassword(redisProperties.getPassword());
            log.info("hidden blade redis standalone init ...");
            return new LettuceConnectionFactory(configuration);
        }
    }
}
