package com.cleo.poi;

import com.cleo.easyexcel.ShopDailyOperations;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @className: com.cleo.poi-> TestPoi
 * @description:
 * @author: cleo
 * @createDate: 2022-01-11 20:54
 * @version: 1.0
 */
public class TestPoi {
    public static void main(String[] args) {
        test2();
    }

    private void test1(){
        POIExcelMake make = new POIExcelMake("xx");

        make.createRow((short) 550);
        make.createCell(13,1).setCellValue("郑铁易行—全球购营业日报明细表");

        make.createRow((short) 300);
        make.createCell().setCellValue("日期");
        make.createCell(12,1).setCellValue("2022-01-10");

        make.createRow((short) 300);
        make.createCell().setCellValue("商品名称");
        make.createCell().setCellValue("销售数量");
        make.createCell().setCellValue("销售单价");
        make.createCell().setCellValue("含税销售收入");
        make.createCell().setCellValue("不含税销售收入");
        make.createCell().setCellValue("销项税率");
        make.createCell().setCellValue("销项税");
        make.createCell().setCellValue("成本含税单价");
        make.createCell().setCellValue("含税成本");
        make.createCell().setCellValue("不含税成本");
        make.createCell().setCellValue("进项税率");
        make.createCell().setCellValue("进项税");
        make.createCell().setCellValue("优惠金额");

//        for(int i=0;i<4;i++){
//            make.createRow((short) 300);
//        }
//
//        make.createRow((short) 300);
//        make.createCell().setCellValue("手续费");
//        make.createRow((short) 300);
//        make.createCell().setCellValue("小计");
//
//
//        make.createRow((short) 500);
//        make.createCell(13,1).setCellValue("PS：灰色标注的单元格为统计数据，手续费以及含税销售收入计算公式见原型文件   其余标注单元格为加和数据计算");




        List<ShopDailyOperations> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ShopDailyOperations operations = new ShopDailyOperations();
            operations.setName(i+"可乐");
            operations.setSellCount(new Random().nextInt(10)*10);
            operations.setPrice(new BigDecimal(10));
            operations.setIncomeTax(new BigDecimal(100));
            operations.setIncomeNotTax(new BigDecimal(100));
            operations.setSellTaxRate(0.15);
            operations.setSellTax(new BigDecimal(100));
            operations.setCostTaxPrice(new BigDecimal(10));
            operations.setCostTax(new BigDecimal(100));
            operations.setCostNotTax(new BigDecimal(100));
            operations.setBuyTaxRate(0.5);
            operations.setBuyTax(new BigDecimal(100));
            operations.setReduceAmount(new BigDecimal(100));
            list.add(operations);

        }

        HSSFSheet sheet = make.getSheet();
        for (ShopDailyOperations operations : list) {
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow row = sheet.createRow(lastRowNum+1);
            row.createCell(0).setCellValue(operations.getName());
            row.createCell(1).setCellValue(operations.getSellCount());
            row.createCell(2).setCellValue(operations.getPrice().toPlainString());
            row.createCell(3).setCellValue(operations.getIncomeTax().toPlainString());
            row.createCell(4).setCellValue(operations.getIncomeNotTax().toPlainString());
            row.createCell(5).setCellValue(operations.getSellTaxRate().toString());
            row.createCell(6).setCellValue( operations.getSellTax().toPlainString());
            row.createCell(7).setCellValue(operations.getCostTaxPrice().toPlainString());
            row.createCell(8).setCellValue(operations.getCostTax().toPlainString());
            row.createCell(9).setCellValue(operations.getCostNotTax().toPlainString());
            row.createCell(10).setCellValue(operations.getCostNotTax().toPlainString());
            row.createCell(11).setCellValue(operations.getBuyTaxRate().toString());
            row.createCell(12).setCellValue(operations.getBuyTax().toPlainString());
            row.createCell(13).setCellValue(operations.getReduceAmount().toPlainString());
        }

        HSSFRow rowsxf = sheet.createRow(sheet.getLastRowNum()+1);
        rowsxf.createCell(0).setCellValue("手续费");

        HSSFRow rowxj = sheet.createRow(sheet.getLastRowNum()+1);
        rowxj.createCell(0).setCellValue("小计");

        make.autoWith();
        HSSFWorkbook hssfWorkbook = make.getHssfWorkbook();

        FileOutputStream stream = null;
        try {
            File file = new File("D:/","xx1.xls");
            stream = new FileOutputStream(file);
            file.deleteOnExit();
            hssfWorkbook.write(stream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void test2(){
        List<ShopDailyOperations> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ShopDailyOperations operations = new ShopDailyOperations();
            operations.setName(i+"可乐");
            operations.setSellCount(new Random().nextInt(10)*10);
            operations.setPrice(new BigDecimal(10));
            operations.setIncomeTax(new BigDecimal(100));
            operations.setIncomeNotTax(new BigDecimal(100));
            operations.setSellTaxRate(0.15);
            operations.setSellTax(new BigDecimal(100));
            operations.setCostTaxPrice(new BigDecimal(10));
            operations.setCostTax(new BigDecimal(100));
            operations.setCostNotTax(new BigDecimal(100));
            operations.setBuyTaxRate(0.5);
            operations.setBuyTax(new BigDecimal(100));
            operations.setReduceAmount(new BigDecimal(100));
            list.add(operations);

        }
        ExportShopDailyOperationsHandler handler = new ExportShopDailyOperationsHandler(list);
        handler.createHeader("2021-12-12");
        handler.filldata();
        handler.createFeetHeader();


        FileOutputStream stream = null;
        try {
            File file = new File("D:/","xx2.xls");
            stream = new FileOutputStream(file);
            file.deleteOnExit();
            handler.getWb().write(stream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
