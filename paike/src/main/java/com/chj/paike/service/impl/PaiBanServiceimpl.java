package com.chj.paike.service.impl;

import com.chj.paike.constant.Constant;
import com.chj.paike.domain.DutyTime;
import com.chj.paike.domain.Student;
import com.chj.paike.service.IJsoupService;
import com.chj.paike.service.IPaiBanService;
import com.chj.paike.utils.ExportExcel;
import com.chj.paike.utils.StudentSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaiBanServiceimpl implements IPaiBanService {

    @Autowired
    private IJsoupService iJsoupService;

    @Override
    public List<DutyTime> paiban(String xhs) {

        //学号以英文逗号相隔
        String[] strings = xhs.split(" ");

        /**
         * 初始化每个时间段的无课学生列表
         */
        List<List<Student>> freeStudentsList = new ArrayList<>();
        List<DutyTime> dutyTimes = new ArrayList<>();

        for(int i=0;i<19;i++){
            List<Student> freeStudents = new ArrayList<>();
            freeStudentsList.add(freeStudents);
        }

        /**
         * 补充学生list及其信息
         */
        for (String xh: strings) {
            Student student = new Student();
            student.setXh(xh);
            student.setName(iJsoupService.getName(xh));
            List<Integer> freeDutyTimes = iJsoupService.getFreeDutyTimes(xh);
            student.setFreeTimes(freeDutyTimes.size());
            for (int time : freeDutyTimes) {
                freeStudentsList.get(time-1).add(student);
            }
        }

        /**
         * 19个值班时间段
         */
        for(int i=0;i<19;i++){
            DutyTime dutyTime = new DutyTime();
            dutyTime.setNum(i+1);
            //获取该时间段无课的学生列表并按照周无课数升序排序
            dutyTime.setFreeStudents(StudentSort.freeTimesSort(freeStudentsList.get(i)));
            List<Student> dutyStudents = new ArrayList<>();
            dutyTime.setDutyStudents(dutyStudents);
            dutyTimes.add(dutyTime);
        }
        //时间段按照每段空闲学生人数升序排序
        List<DutyTime> newdutyTimes = StudentSort.dutyTimesSort(dutyTimes);

        /**
         * 时间段循环排班
         */
        for(int w=0;w<3;w++){
            for(DutyTime t:newdutyTimes){
                List<Student> freeStudents = t.getFreeStudents();
                List<Student> dutyStudents = t.getDutyStudents();
                if(dutyStudents.size()<3){
                    if(freeStudents.size()>0){

                        //找到未被安排两次的学生
                        int index = 0;
                        Student student = freeStudents.get(index);
                        while (student.getFlag()>= Constant.FALG_TWO){
                            index++;
                            if(index == freeStudents.size()){
                                break;
                            }
                            student = freeStudents.get(index);
                        }

                        if (student.getFlag()<Constant.FALG_TWO){
                            dutyStudents.add(student);
                            freeStudents.remove(student);
                            //被安排，flag+1
                            student.changeFlag();
                        }
                    }
                }
            }
        }

        StudentSort.dutyTimesSortByNum(dutyTimes);
        return dutyTimes;
    }

    @Override
    public void createExcel(List<DutyTime> dutyTimes) throws IOException {
        ExportExcel.createExcel(dutyTimes);
    }


}
