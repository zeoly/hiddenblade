package com.yahacode.hiddenblade.app.support;

import com.yahacode.hiddenblade.app.cache.RedisProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Easy String redis cache operator
 *
 * @author zengyongli
 * @since 2022/03/31
 */
public class RedisOperator {

    private RedisProperties properties;

    private RedisTemplate<String, String> redisTemplate;

    public RedisOperator(RedisProperties properties, RedisTemplate redisTemplate) {
        this.properties = properties;
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, String value) {
        set(key, value, Duration.ofSeconds(properties.getDefaultTimeout()));
    }

    public void set(String key, String value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Boolean expire(String key) {
        return expire(key, Duration.ofSeconds(properties.getDefaultTimeout()));
    }

    public Boolean expire(String key, Duration duration) {
        return redisTemplate.expire(key, duration);
    }

    public long addMember(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public Set<String> getMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public long deleteMember(String key, String value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    public Set<String> scan(String pattern) {
        Set<String> keySet = new HashSet<>();
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(pattern).build());
        while (cursor.hasNext()) {
            keySet.add(new String(cursor.next()));
        }
        return keySet;
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Boolean lock(String key, String value, Duration duration) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, duration);
    }

    public Boolean unlock(String key, String value) {
        String command = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(command, Boolean.class);
        return redisTemplate.execute(script, Arrays.asList(key), value);
    }
}
