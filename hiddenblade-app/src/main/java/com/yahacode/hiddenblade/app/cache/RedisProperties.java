package com.yahacode.hiddenblade.app.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    private static Logger log = LoggerFactory.getLogger(RedisProperties.class);

    RedisMode mode = RedisMode.CLUSTER;

    List<String> nodes;

    String password;

    long defaultTimeout = 300;

    private Map<String, Integer> customTimeout;

    private int maxIdle = 10;

    private int minIdle = 10;

    private int maxActive = 20;

    private int maxWait = 2000;

    private int commandTimeout = 2000;

    private int shutdownTimeout = 2000;

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

    public Map<String, Integer> getCustomTimeout() {
        return customTimeout;
    }

    public void setCustomTimeout(Map<String, Integer> customTimeout) {
        this.customTimeout = customTimeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public int getShutdownTimeout() {
        return shutdownTimeout;
    }

    public void setShutdownTimeout(int shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }

}
