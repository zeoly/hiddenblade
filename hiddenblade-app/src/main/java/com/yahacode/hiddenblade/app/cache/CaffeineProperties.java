package com.yahacode.hiddenblade.app.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * caffeine cache configuration properties
 *
 * @author zengyongli
 * @since 2022/04/15
 */
@ConfigurationProperties(prefix = "hb.caffeine")
public class CaffeineProperties {

    private List<String> caches;

    private Map<String, SingleCache> config = new HashMap<>();

    public static class SingleCache {

        long timeout = 300;

        private int initSize = 30;

        private int maxSize = 100;

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }

        public int getInitSize() {
            return initSize;
        }

        public void setInitSize(int initSize) {
            this.initSize = initSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }
    }

    public List<String> getCaches() {
        return caches;
    }

    public void setCaches(List<String> caches) {
        this.caches = caches;
    }

    public Map<String, SingleCache> getConfig() {
        return config;
    }

    public void setConfig(Map<String, SingleCache> config) {
        this.config = config;
    }

    @PostConstruct
    void postConstruct() {
        for (String cache : caches) {
            if (!config.containsKey(cache)) {
                config.put(cache, new SingleCache());
            }
        }
    }
}
