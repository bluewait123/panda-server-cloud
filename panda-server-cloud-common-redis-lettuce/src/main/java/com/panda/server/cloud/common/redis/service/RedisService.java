package com.panda.server.cloud.common.redis.service;

import com.google.gson.Gson;
import com.panda.server.cloud.common.utils.GsonListParameterizedType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 缓存管理
 * 相当于使用redis-cli操作redis
 * @author w
 * @date 2020-06-29
 */
@Slf4j
@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Gson gson;

    /**
     * 设置缓存
     * @param key Key
     * @param value VALUE
     */
    public void put(String key,String value){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置缓存
     * @param key Key
     * @param value VALUE
     */
    public void put(String key,String value, long expireTime){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key,value,expireTime);
    }

    /**
     * 设置缓存
     * @param key Key
     * @param value VALUE
     */
    public void putObject(String key,Object value){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key,gson.toJson(value));
    }

    /**
     * 设置缓存
     * @param key Key
     * @param value VALUE
     * @param expireTime 有效期 默认单位毫秒
     */
    public void putObject(String key,Object value, Long expireTime){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key,gson.toJson(value),expireTime);
    }

    /**
     * 设置缓存 List
     * @param key key
     * @param list list
     */
    public void putList(String key, List list){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key, gson.toJson(list));
    }

    /**
     * 设置缓存 List
     * @param key key
     * @param list list
     * @param expireTime 有效期 默认单位毫秒
     */
    public void putList(String key, List list, long expireTime){
        log.info("key:{}",key);
        redisTemplate.opsForValue().set(key, gson.toJson(list), expireTime);
    }

    /**
     * 获取缓存数据
     * @param key KEY
     */
    public String get(String key){
        log.info("key:{}",key);
        String value = (String) redisTemplate.opsForValue().get(key);
        log.debug("value:{}",value);
        return value;
    }

    public Integer getInteger(String key){
        log.info("key:{}",key);
        Integer value = (Integer) redisTemplate.opsForValue().get(key);
        log.debug("value:{}",value);
        return value;
    }

    /**
     * 获取缓存数据
     * @param key KEY
     */
    public <T> T getObject(String key, Class<T> cls){
        return gson.fromJson(get(key),cls);
    }

    /**
     * 获取数据并转换成List对象
     * @param key key
     * @param itemClass 子对象
     * @return List
     */
    public <T> List<T> getList(String key, Class itemClass){
        return gson.fromJson(get(key),new GsonListParameterizedType(itemClass));
    }
}
