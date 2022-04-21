package com.yahacode.hiddenblade.app.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis configuration properties
 *
 * @author zengyongli
 * @since 2022/04/12
 */
@Component
@ConditionalOnProperty(prefix = "hb.redis", value = "nodes")
@ConfigurationProperties(prefix = "hb.redis")
public class RedisProperties {

    RedisMode mode = RedisMode.STANDALONE;

    List<String> nodes;

    String password;

    long defaultTimeout = 300;

    public RedisMode getMode() {
        return mode;
    }

    public void setMode(RedisMode mode) {
        this.mode = mode;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }
}
