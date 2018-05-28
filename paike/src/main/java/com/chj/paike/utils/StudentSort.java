package com.chj.paike.utils;

import com.chj.paike.domain.DutyTime;
import com.chj.paike.domain.Student;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {

    public static List<Student> freeTimesSort(List<Student> freeStudents){
        Collections.sort(freeStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                //按照Student的周无课数进行升序排列
                if(s1.getFreeTimes() > s2.getFreeTimes()){
                    return 1;
                }
                if(s1.getFreeTimes() == s2.getFreeTimes()){
                    return 0;
                }
                return -1;
            }
        });
        return freeStudents;
    }

    public static List<DutyTime> dutyTimesSort(List<DutyTime> dutyTimeList){
        Collections.sort(dutyTimeList, new Comparator<DutyTime>() {
            @Override
            public int compare(DutyTime d1, DutyTime d2) {
                //按照Student的周无课数进行升序排列
                if(d1.getFreeStudents().size() > d2.getFreeStudents().size()){
                    return 1;
                }
                if(d1.getFreeStudents().size() == d2.getFreeStudents().size()){
                    return 0;
                }
                return -1;
            }
        });
        return dutyTimeList;
    }

    /**
     * dutyTimeList按照名称排序
     * @param dutyTimeList
     * @return
     */
    public static List<DutyTime> dutyTimesSortByNum(List<DutyTime> dutyTimeList){
        Collections.sort(dutyTimeList, new Comparator<DutyTime>() {
            @Override
            public int compare(DutyTime d1, DutyTime d2) {
                //按照Student的周无课数进行升序排列
                if(d1.getNum() > d2.getNum()){
                    return 1;
                }
                if(d1.getNum() == d2.getNum()){
                    return 0;
                }
                return -1;
            }
        });
        return dutyTimeList;
    }
}
