package com.cleo.poi;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * @description:
 * @date: 2020/3/12 11:55
 * @author: xsz
 */
public class XSSFWorkbookStyleUtil {
    private static XSSFCellStyle xssfCellStyle;
    private static XSSFCellStyle xssfcellstyletwo;
    private static XSSFCellStyle headerStyle;
    private static XSSFCellStyle titleStyleOne;
    private static XSSFCellStyle titleStyleTwo;


    public static  void createTitle(XSSFSheet sheet, String title) {
        XSSFRow row1 = sheet.createRow(0);
        // 设备标题的高度
        row1.setHeightInPoints(27);
        // 创建标题第一列
        XSSFCell cell1 = row1.createCell(0);
        // 设置值标题
        cell1.setCellValue(title);
        // 设置标题样式
        cell1.setCellStyle(titleStyleOne);
        XSSFRow row2 = sheet.createRow(1);
        // 创建标题第一列
        XSSFCell cell2 = row2.createCell(0);
        // 设置标题样式
        cell2.setCellStyle(titleStyleTwo);
    }

    /**
     * 表格标题样式1
     *
     * @author liben
     * @date 2018/12/6 10:39
     */
    public static  XSSFCellStyle getTitleStyleOne(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex()); //设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 创建字体样式
        Font headerFont = wb.createFont();
        headerFont.setBold(true); // 字体加粗
        headerFont.setFontName("宋体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 18);
        style.setFont(headerFont);
        return style;
    }

    /**
     * 表格标题样式2
     *
     * @author liben
     * @date 2018/12/6 10:39
     */
    public static  XSSFCellStyle getTitleStyleTwo(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);//居右
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直方向
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex()); //设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 创建字体样式
        Font headerFont = wb.createFont();
        headerFont.setBold(true); // 字体加粗
        headerFont.setFontName("宋体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 11);
        style.setFont(headerFont);
        return style;
    }

    /**
     * 表格标题样式1
     *
     * @author liben
     * @date 2018/12/6 10:39
     */
    public static  XSSFCellStyle getHeaderStyleOne(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);//自动换行
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直方向
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex()); //设置背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        // 创建字体样式
        Font headerFont = wb.createFont();
        headerFont.setBold(true); // 字体加粗
        headerFont.setFontName("宋体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 11);
        style.setFont(headerFont);
        return style;
    }

    /**
     * 表格单元格样式1
     *
     * @author liben
     * @date 2018/12/6 10:39
     */
    public static  XSSFCellStyle getXSSFCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直方向
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        // 创建字体样式
        Font headerFont = wb.createFont();
        headerFont.setFontName("宋体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 11);
        style.setFont(headerFont);
        return style;
    }

    /**
     * 表格单元格样式1
     *
     * @author liben
     * @date 2018/12/6 10:39
     */
    public static XSSFCellStyle getXSSFCellStyleTwo(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直方向
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        // 创建字体样式
        Font headerFont = wb.createFont();
        headerFont.setFontName("宋体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 11);
        style.setFont(headerFont);
        return style;
    }

}
