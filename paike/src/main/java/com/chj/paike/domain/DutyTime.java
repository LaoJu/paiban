package com.chj.paike.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 值班时间段实体
 */
@Data
public class DutyTime {
    /**
     * 时间段名称
     */
    //TODO 后可用矩阵坐标代替
    int num;

    /**
     * 在此时间段无课的学生list
     */
    List<Student> freeStudents = new ArrayList<>();

    /**
     * 在此时间段值班的学生list
     */
    List<Student> dutyStudents = new ArrayList<>();


}
