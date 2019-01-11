package com.shawn.weatherbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Authod shawn
 * @create 2019/1/11 0011
 */
@RestController
public class Hello {

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }
}
