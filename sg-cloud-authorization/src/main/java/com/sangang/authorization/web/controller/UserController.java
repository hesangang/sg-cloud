package com.sangang.authorization.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@RestController
public class UserController {

    @GetMapping("/zxg")
    public String zxg(){
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "zzzs";
    }

    @GetMapping("/userInfo")
    public Map<String, Object> userInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Collections.singletonMap("authentication",authentication);
    }
}
