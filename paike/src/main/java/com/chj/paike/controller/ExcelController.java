package com.chj.paike.controller;

import com.chj.paike.domain.DutyTime;
import com.chj.paike.domain.Student;
import com.chj.paike.service.IPaiBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paiban")
public class ExcelController {
    @Autowired
    private IPaiBanService iPaiBanService;

    @PostMapping("/excel")
    public String getPaiBanExcel(@RequestParam(name = "xhs")String xhs, HttpServletResponse response, Map<String ,Object> map) throws IOException {
        List<DutyTime> dutyTimes = iPaiBanService.paiban(xhs);
        List<String> strings = new ArrayList<>();
        for(DutyTime time:dutyTimes){
            String names = "";
            for(Student student:time.getDutyStudents()){
                names = names+" "+student.getName();
            }
            strings.add(names);
            System.out.println(names);
        }
        map.put("a",strings.get(0));
        map.put("b",strings.get(1));
        map.put("c",strings.get(2));
        map.put("d",strings.get(3));
        map.put("e",strings.get(4));
        map.put("f",strings.get(5));
        map.put("g",strings.get(6));
        map.put("h",strings.get(7));
        map.put("i",strings.get(8));
        map.put("j",strings.get(9));
        map.put("k",strings.get(10));
        map.put("l",strings.get(11));
        map.put("m",strings.get(12));
        map.put("n",strings.get(13));
        map.put("o",strings.get(14));
        map.put("p",strings.get(15));
        map.put("q",strings.get(16));
        map.put("r",strings.get(17));
        map.put("s",strings.get(18));
        return "show";
    }


}
