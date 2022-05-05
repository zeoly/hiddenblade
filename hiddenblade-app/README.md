## Cache

### Redis

> primary

```properties
hb.redis.nodes=127.0.0.1:1234
hb.redis.password=foobar

hb.redis.mode=standalone
hb.redis.default-timeout=300
hb.redis.max-idle=10
hb.redis.min-idle=10
hb.redis.max-active=20
hb.redis.max-wait=2000
hb.redis.command-timeout=2000
hb.redis.shutdown-timeout=2000
hb.redis.custom-timeout[CACHE_KEY]=100
```

```java
@Cacheable(value = "CACHE_KEY", key = "#key")
public String getValueByKey(String key) {
    return getKey();
}
```

### Caffeine

```properties
hb.caffeine.caches=KEY1,KEY2

hb.caffeine.config[KEY2].timeout=300
hb.caffeine.config[KEY2].init-size=30
hb.caffeine.config[KEY2].max-size=100
```