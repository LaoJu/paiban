package com.chj.paike.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("name","233");
        return "hello";
    }
}
