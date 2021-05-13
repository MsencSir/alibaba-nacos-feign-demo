package net.chain.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
public class Redis {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public static final String LOCKED = "1";



    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, Integer expire){
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public Long getTtl(String key){
        return redisTemplate.getExpire(key);
    }

    public Boolean lock(String key) {
        return redisTemplate.opsForValue().setIfAbsent(key, "1", 60, TimeUnit.SECONDS);
    }

    public Boolean trylock(String key){
        return redisTemplate.opsForValue().setIfAbsent(key, "1", 60, TimeUnit.SECONDS);
    }

    public Boolean unlock(String key){
        return redisTemplate.delete(key);
    }

    public String popOneInSet(String key){
        return redisTemplate.opsForSet().pop(key);
    }

    public Set<String> membersInSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    public void setOneInSet(String key, String value){
        redisTemplate.opsForSet().add(key, value);
    }

    public String getOneInHash(String name, String key){
        return redisTemplate.opsForHash().get(name, key).toString();
    }

    public void setOneInHash(String name, String key, String value){
        redisTemplate.opsForHash().put(name, key, value);
    }

    public Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }

    public Boolean setIfAbsent(String key, String value, Integer timeout){
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    public long getIncrementNumByKey(String key, long increment) {

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        Long result = operations.increment(key, increment);

        return result;
    }
    public Boolean hasKey(String key){
        return  redisTemplate.hasKey(key);
    }
    public void incre(String key,Long count){
        redisTemplate.opsForValue().increment(key,count);
    }
    public String getMap(String key, String name){
        Object obj = redisTemplate.opsForHash().get(key, name);
        if(obj == null){
            return null;
        }
        return obj.toString();
    }

    public void setMap(String key, String name, String value){
        redisTemplate.opsForHash().put(key, name, value);
    }

    public void rename(String oldKey,String newKey){
        redisTemplate.rename(oldKey,newKey);
    }

    public void multi(){
        redisTemplate.multi();
    }
    public List<Object> exec(){
        return redisTemplate.exec();
    }
    public void discard(){
        redisTemplate.discard();
    }

    public void setEnableTransactionSupport(){
        redisTemplate.setEnableTransactionSupport(true);
    }
}
