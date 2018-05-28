package com.chj.paike.service;

import com.chj.paike.domain.DutyTime;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IPaiBanService {
    public List<DutyTime> paiban(String xhs);
    public void  createExcel(List<DutyTime> dutyTimes) throws IOException;
}
