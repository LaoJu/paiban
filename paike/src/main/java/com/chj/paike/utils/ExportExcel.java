package com.chj.paike.utils;

import com.chj.paike.constant.Constant;
import com.chj.paike.domain.DutyTime;
import com.chj.paike.domain.Student;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class ExportExcel{

    public static void createExcel(List<DutyTime> dutyTimes) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("排班表");

        //设置标题字体样式
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("黑体");//字体
        titleFont.setFontHeightInPoints((short)18);//字号
        titleFont.setBold(true);//加粗

        //创建标题单元格样式
        HSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setFont(titleFont);


        //创建标题合并单元格
        CellRangeAddress cra = new CellRangeAddress(0,0,0,5);
        sheet.addMergedRegion(cra);
        //创建第一行标题
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell titleCell = row0.createCell(0);
        titleCell.setCellStyle(titleCellStyle);
        titleCell.setCellValue("学生创新创业中心助理团助理值班表");

        //创建周一到周五标题
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeightInPoints(40);
        for(int i=0;i<6;i++){
            if(i!=0){
                HSSFCell cell = row1.createCell(i);
                cell.setCellValue("星期"+i);
            }else {
                HSSFCell cell = row1.createCell(0);
            }
        }

        //3.4节
        HSSFRow row2 = sheet.createRow(2);
        row2.setHeightInPoints(80);
        for(int i = 0;i<6;i++){
            if(i==0){
                HSSFCell cell = row2.createCell(i);
                cell.setCellValue("三四节");
            }else{
                HSSFCell cell = row2.createCell(i);
                StringBuilder stringBuilder = new StringBuilder();
                for (Student student:dutyTimes.get(i-1).getDutyStudents()){
                    stringBuilder.append(student.getName()+" ");
                }
                cell.setCellValue(stringBuilder.toString());
            }
        }

        //5.6节
        HSSFRow row3 = sheet.createRow(3);
        row3.setHeightInPoints(80);
        for(int i = 0;i<6;i++){
            if(i==0){
                HSSFCell cell = row3.createCell(i);
                cell.setCellValue("五六节");
            }else{
                HSSFCell cell = row3.createCell(i);
                StringBuilder stringBuilder = new StringBuilder();
                for (Student student:dutyTimes.get(i+4).getDutyStudents()){
                    stringBuilder.append(student.getName()+" ");
                }
                cell.setCellValue(stringBuilder.toString());
              }
            }


        //7.8节
        HSSFRow row4 = sheet.createRow(4);
        row4.setHeightInPoints(80);
        for(int i = 0;i<6;i++){
            if(i==0){
                HSSFCell cell = row4.createCell(i);
                cell.setCellValue("七八节");

            }else{
                HSSFCell cell = row4.createCell(i);
                StringBuilder stringBuilder = new StringBuilder();
                for (Student student:dutyTimes.get(i+9).getDutyStudents()){
                    stringBuilder.append(student.getName()+" ");
                }
                cell.setCellValue(stringBuilder.toString());
            }

        }
        //9.10节
        HSSFRow row5 = sheet.createRow(5);
        row5.setHeightInPoints(80);
        for(int i = 0;i<6;i++){
            if(i==0){
                HSSFCell cell = row5.createCell(i);
                cell.setCellValue("九十节");
            }else if(i == 5){
                HSSFCell cell = row5.createCell(i);
            }else{
                HSSFCell cell = row5.createCell(i);
                StringBuilder stringBuilder = new StringBuilder();
                for (Student student:dutyTimes.get(i+14).getDutyStudents()){
                stringBuilder.append(student.getName()+" ");
                }
                cell.setCellValue(stringBuilder.toString());
                }

        }

        //创建一个文件夹，写入数据
        String path = Constant.FILE_PATH;
        File file = new File(path);
        workbook.write(file);

        //下载文件
        //download(response);
    }

    private static void download(HttpServletResponse response){

        //返回Excel表格。共用户下载
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = new File(Constant.FILE_PATH);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

