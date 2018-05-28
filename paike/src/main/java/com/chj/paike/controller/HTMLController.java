package com.chj.paike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 用于访问HTML文件
 * Created by xfh on 2018/4/1.
 */
@Controller
@RequestMapping("/cxcy")
public class HTMLController {

    @RequestMapping("/home")
    public String getHTML(){
        return "index";
    }

    @RequestMapping("/downHtml")
    public String getDownHTML(){
            return "downExcel";
    }
 }
