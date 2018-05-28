package com.chj.paike.service.impl;

import com.chj.paike.service.IJsoupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupServiceImplTest {
    @Autowired
    private IJsoupService iJsoupService;

    @Test
    public void getName() throws Exception {
        System.out.println(iJsoupService.getName("2016210888"));
    }

    @Test
    public void getFreeDutyTimes() throws Exception {
        iJsoupService.getFreeDutyTimes("2016210888");
    }

}