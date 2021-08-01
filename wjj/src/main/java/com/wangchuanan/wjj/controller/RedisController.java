package com.wangchuanan.wjj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis验证controller，仅作为示范
 *
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis取值
     * @param key
     * @return
     */
    @GetMapping("/redis/get/{key}")
    public Object get(@PathVariable("key") String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * redis塞值
     * @param key
     * @param value
     * @return
     */
    @PostMapping("/redis/set/{key}/{value}")
    public Object set(@PathVariable("key") String key,@PathVariable("value") String value){
        redisTemplate.opsForValue().set(key,value);
        return "set success";
    }


}
