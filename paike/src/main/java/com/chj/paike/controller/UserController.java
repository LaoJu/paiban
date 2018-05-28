package com.chj.paike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cxcy")
public class UserController {

    @PostMapping("/downHtml")
    public String login(@RequestParam(name = "pwd")String pwd){
        String password = "611611";
        if(pwd.equals(password)){
            return "/downExcel";
        }else {
            return "/wrong";
        }
    }
}
