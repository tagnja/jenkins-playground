package com.taogen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class App {

    private Jedis jedis = new Jedis("redis", 6379);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public Jedis getJedis() {
        jedis.auth("yourpassword");
        return jedis;
    }

    @GetMapping("/")
    public String hello() {
        return "hello! - " + System.currentTimeMillis();
    }

    @GetMapping("/add")
    public String add() {
        try {
            getJedis().sadd("dates", new Date().toString());
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
        return "ok";
    }

    @GetMapping("/list")
    public String list() {
        return getJedis().smembers("dates").toString();
    }
}
