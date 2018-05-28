package com.chj.paike.domain;

import lombok.Data;

/**
 * 学生实体类
 */
@Data
public class Student {
    /**
     * 学生姓名
     */
    String name;

    String xh;

    /**
     * 一周无课时间总数
     */
    int freeTimes = 0;

    /**
     * 学生每周值班两次，被安排一次flag置1,安排两次置2
     */
    int flag = 0;

    public  void changeFlag(){
        this.flag++;
    }
}
