package com.hxh.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ExcelParserController
 * @Description
 * @Author huxuanhao
 * @Date 2023/11/11 14:01
 */
@Controller
@RequestMapping("/excel")
public class ExcelParserController {
    @RequestMapping("/upload")
    public String analysisExcel(@RequestParam("file") MultipartFile file , HttpServletRequest servletRequest) throws IOException {
        // TODO: 2023/11/12  添加一个上传日志，保留excel名称和上传时间 
        String filePath = "C:/Users/86186/Desktop/2023年11月排班.xlsx";
        // 替换为你的工作表名称
        String sheetName = "汇总";

        // 替换为你想要读取的行和列的索引
        int rowIndex = 0;
        int cellIndex = 0;
        int dateCellIndex = 0;

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheet(sheetName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String today = simpleDateFormat.format(date);
        // 遍历每一行
        for (Row row : sheet) {
            // 遍历每一列
            for (Cell cell : row) {
                // 判断单元格内容是否包含目标内容
                if(cell.getRowIndex() == 0 && cell.getColumnIndex() > 0){
                    String cellDate = simpleDateFormat.format(cell.getDateCellValue().getTime());
                    if (cellDate.contains(today)) {
                        dateCellIndex = cell.getColumnIndex();
                        continue;
                    }
                }
                if (cell.toString().contains("李曼曼")) {
                    rowIndex = row.getRowNum();
                    // 输出找到的内容以及行和列索引
                     break;
                }
            }
        }
        //白班列
        int dayCellIndex = 0;
        //晚班列
        int nightCellIndex = 0;
        //白加晚列
        int allDayCellIndex = 0;
        //休息列
        int restCellIndex = 0;
        //只统计遍历出的第一个班次
        int dayCount = 0;
        int nightCount = 0;
        int allDayCount = 0;
        int restCount = 0;
        for (Cell cell : sheet.getRow(rowIndex)) {
            if(cell.getColumnIndex() > dateCellIndex){
                if(cell.toString().contains("白班") && dayCount == 0 ){
                    dayCellIndex = cell.getColumnIndex();
                    dayCount++;
                }else if(cell.toString().contains("白加晚") && allDayCount == 0 ){
                    allDayCellIndex = cell.getColumnIndex();
                    allDayCount++;
                }else if (cell.toString().contains("晚班") && nightCount == 0 ){
                    nightCellIndex = cell.getColumnIndex();
                    nightCount++;
                }else if(cell.toString().contains("休息") && restCount == 0 ){
                    restCellIndex = cell.getColumnIndex();
                    restCount++;
                }else {

                }
            }
        }
       //下次白班日期
        String dayTime = simpleDateFormat.format(sheet.getRow(0).getCell(dayCellIndex).getDateCellValue().getTime());
        String dayWeek = sheet.getRow(1).getCell(dayCellIndex).getStringCellValue();
        //下次晚班日期
        String nightTime = simpleDateFormat.format(sheet.getRow(0).getCell(nightCellIndex).getDateCellValue().getTime());
        String nightWeek = sheet.getRow(1).getCell(nightCellIndex).getStringCellValue();
        //下次休息日期
        String restTime = simpleDateFormat.format(sheet.getRow(0).getCell(restCellIndex).getDateCellValue().getTime());
        String restWeek = sheet.getRow(1).getCell(restCellIndex).getStringCellValue();
        //下次白加晚日期
        String allDayTime = simpleDateFormat.format(sheet.getRow(0).getCell(allDayCellIndex).getDateCellValue().getTime());
        String allDayWeek = sheet.getRow(1).getCell(allDayCellIndex).getStringCellValue();
        workbook.close();
        Map<String, String> map = new HashMap<>();
        map.put("dayTime",dayTime);
        map.put("nightTime",nightTime);
        map.put("restTime",restTime);
        map.put("dayWeek",dayWeek);
        map.put("nightWeek",nightWeek);
        map.put("restWeek",restWeek);
        map.put("allDayWeek",allDayWeek);
        map.put("allDayTime",allDayTime);
        servletRequest.setAttribute("map",map);
        fileInputStream.close();
        return "index";
    }
    @RequestMapping("/init")
    public String initExcel(HttpServletRequest servletRequest) throws IOException {
        String filePath = "C:/Users/86186/Desktop/2023年11月排班.xlsx";
        // 替换为你的工作表名称
        String sheetName = "汇总";

        // 替换为你想要读取的行和列的索引
        int rowIndex = 0;
        int cellIndex = 0;
        int dateCellIndex = 0;

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String today = simpleDateFormat.format(date);
        // 遍历每一行
        for (Row row : sheet) {
            // 遍历每一列
            for (Cell cell : row) {
                // 判断单元格内容是否包含目标内容
                if(cell.getRowIndex() == 0 && cell.getColumnIndex() > 0){
                    String cellDate = simpleDateFormat.format(cell.getDateCellValue().getTime());
                    if (cellDate.contains(today)) {
                        dateCellIndex = cell.getColumnIndex();
                        continue;
                    }
                }
                if (cell.toString().contains("李曼曼")) {
                    rowIndex = row.getRowNum();
                    // 输出找到的内容以及行和列索引
                    break;
                }
            }
        }
        //白班列
        int dayCellIndex = 0;
        //晚班列
        int nightCellIndex = 0;
        //白加晚列
        int allDayCellIndex = 0;
        //休息列
        int restCellIndex = 0;
        //只统计遍历出的第一个班次
        int dayCount = 0;
        int nightCount = 0;
        int allDayCount = 0;
        int restCount = 0;
        for (Cell cell : sheet.getRow(rowIndex)) {
            if(cell.getColumnIndex() > dateCellIndex){
                if(cell.toString().contains("白班") && dayCount == 0 ){
                    dayCellIndex = cell.getColumnIndex();
                    dayCount++;
                }else if(cell.toString().contains("白加晚") && allDayCount == 0 ){
                    allDayCellIndex = cell.getColumnIndex();
                    allDayCount++;
                }else if (cell.toString().contains("晚班") && nightCount == 0 ){
                    nightCellIndex = cell.getColumnIndex();
                    nightCount++;
                }else if(cell.toString().contains("休息") && restCount == 0 ){
                    restCellIndex = cell.getColumnIndex();
                    restCount++;
                }else {

                }
            }
        }
        //下次白班日期
        String dayTime = simpleDateFormat.format(sheet.getRow(0).getCell(dayCellIndex).getDateCellValue().getTime());
        String dayWeek = sheet.getRow(1).getCell(dayCellIndex).getStringCellValue();
        //下次晚班日期
        String nightTime = simpleDateFormat.format(sheet.getRow(0).getCell(nightCellIndex).getDateCellValue().getTime());
        String nightWeek = sheet.getRow(1).getCell(nightCellIndex).getStringCellValue();
        //下次休息日期
        String restTime = simpleDateFormat.format(sheet.getRow(0).getCell(restCellIndex).getDateCellValue().getTime());
        String restWeek = sheet.getRow(1).getCell(restCellIndex).getStringCellValue();
        //下次白加晚日期
        String allDayTime = simpleDateFormat.format(sheet.getRow(0).getCell(allDayCellIndex).getDateCellValue().getTime());
        String allDayWeek = sheet.getRow(1).getCell(allDayCellIndex).getStringCellValue();
        workbook.close();
        Map<String, String> map = new HashMap<>();
        map.put("dayTime",dayTime);
        map.put("nightTime",nightTime);
        map.put("restTime",restTime);
        map.put("dayWeek",dayWeek);
        map.put("nightWeek",nightWeek);
        map.put("restWeek",restWeek);
        map.put("allDayWeek",allDayWeek);
        map.put("allDayTime",allDayTime);
        servletRequest.setAttribute("map",map);
        fileInputStream.close();
        return "index";
    }
}