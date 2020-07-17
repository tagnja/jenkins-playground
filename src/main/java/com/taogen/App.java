package com.taogen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App{

    private Jedis jedis = new Jedis("localhost", 6379);

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String hello()
    {
        return "hello! - " + System.currentTimeMillis();
    }

    @GetMapping("/add")
    public String add(){
        try {
            jedis.sadd("dates", new Date().toString());
        } catch (Exception e){
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
        return "ok";
    }

    @GetMapping("/list")
    public String list(){
        return jedis.smembers("dates").toString();
    }
}
