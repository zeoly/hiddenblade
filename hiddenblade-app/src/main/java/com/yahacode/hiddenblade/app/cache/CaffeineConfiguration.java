package com.yahacode.hiddenblade.app.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Caffeine cache configuration
 *
 * @author zengyongli
 * @since 2022/04/15
 */
@EnableCaching
@Configuration
@ConditionalOnProperty(prefix = "hb.caffeine", value = "caches")
@EnableConfigurationProperties(CaffeineProperties.class)
public class CaffeineConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CaffeineConfiguration.class);

    @Autowired
    CaffeineProperties properties;

    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(buildCaches());
        log.info("init caffeine manager ...");
        return manager;
    }

    private List<CaffeineCache> buildCaches() {
        List<CaffeineCache> caches = new ArrayList<>();
        for (String key : properties.getConfig().keySet()) {
            CaffeineProperties.SingleCache singleCache = properties.getConfig().get(key);
            Cache cache = Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(singleCache.getTimeout()))
                    .initialCapacity(singleCache.getInitSize()).maximumSize(singleCache.getMaxSize()).build();
            caches.add(new CaffeineCache(key, cache));
            log.info("init caffeine cache: {}", key);
        }
        return caches;
    }
}
