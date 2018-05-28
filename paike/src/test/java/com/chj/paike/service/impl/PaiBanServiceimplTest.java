package com.chj.paike.service.impl;

import com.chj.paike.domain.DutyTime;
import com.chj.paike.service.IPaiBanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PaiBanServiceimplTest {

    @Autowired
    private IPaiBanService iPaiBanService;
    //response 未添加 测试
    @Test
    public void paiban() throws Exception {
        List<DutyTime> dutyTimes = iPaiBanService.paiban("2015214479 2015213418 2015211459 2015212096 2016212892 2015210648 2016214973 2016214902 2016211240 2015214478 2016214636 2016214500 2015212920 2016213451 2016213340 2016210811");
        iPaiBanService.createExcel(dutyTimes);
    }

}