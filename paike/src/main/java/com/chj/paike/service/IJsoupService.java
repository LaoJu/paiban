package com.chj.paike.service;


import java.util.List;

/**
 * 爬虫获取学生姓名，学生课表
 */
public interface IJsoupService {
    /**
     * 获取学生学号
     * @param xh
     * @return
     */
    public String getName(String xh);

    /**
     * 获取学生无课时段list
     * @param xh
     * @return
     */
    public List<Integer> getFreeDutyTimes(String xh);
}
