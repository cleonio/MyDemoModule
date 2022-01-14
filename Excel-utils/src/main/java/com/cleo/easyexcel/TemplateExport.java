package com.cleo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * @className: com.cleo.easyexcel-> TemplateExport
 * @description:
 * @author: cleo
 * @createDate: 2022-01-12 9:27
 * @version: 1.0
 */
public class TemplateExport {

    public static void main(String[] args) {



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
//        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//        for (ShopDailyOperations operations : list) {
//            Map<String, String> lm = new HashMap<String, String>();
//            /*lm.put("name", operations.getName());
//            lm.put("sellCount", operations.getSellCount().toString());
//            lm.put("price", operations.getPrice().toPlainString());
//            lm.put("incomeTax", operations.getIncomeTax().toPlainString());
//            lm.put("incomeNotTax", operations.getIncomeNotTax().toPlainString());
//            lm.put("sellTaxRate",operations.getBuyTaxRate().toString());
//            lm.put("sellTax", operations.getSellTax().toPlainString());
//            lm.put("costTaxPrice", operations.getCostTaxPrice().toPlainString());
//            lm.put("costTax", operations.getCostTax().toPlainString());
//            lm.put("costNotTax", operations.getCostNotTax().toPlainString());
//            lm.put("buyTaxRate", operations.getBuyTaxRate().toString());
//            lm.put("buyTax", operations.getBuyTax().toPlainString());
//            lm.put("reduceAmount", operations.getReduceAmount().toPlainString());*/
//            lm.put("a", operations.getName());
//            lm.put("b", operations.getSellCount().toString());
//            lm.put("c", operations.getPrice().toPlainString());
//            lm.put("d", operations.getIncomeTax().toPlainString());
//            lm.put("e", operations.getIncomeNotTax().toPlainString());
//            lm.put("f",operations.getBuyTaxRate().toString());
//            lm.put("g", operations.getSellTax().toPlainString());
//            lm.put("h", operations.getCostTaxPrice().toPlainString());
//            lm.put("i", operations.getCostTax().toPlainString());
//            lm.put("j", operations.getCostNotTax().toPlainString());
//            lm.put("k", operations.getBuyTaxRate().toString());
//            lm.put("l", operations.getBuyTax().toPlainString());
//            lm.put("m", operations.getReduceAmount().toPlainString());
//            listMap.add(lm);
//        }

       // map.put("list", listMap);


        InputStream templateFileName = TemplateExport.class.getClassLoader().getResourceAsStream("a.xlsx");
        //InputStream templateFileName = this.getClass().getResourceAsStream("/ShopDailyOperationsTemplate.xlsx");
        String fileName ="D://"+ "simpleFill" + System.currentTimeMillis() + ".xlsx";
        //EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);

        ExcelWriter excelWriter = EasyExcel.write(fileName)
                .withTemplate(templateFileName)
                .build();

        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(list,fillConfig,writeSheet);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2021-10-10");
        map.put("sumSellCount", 10000);
        excelWriter.fill(map,writeSheet);
        excelWriter.finish();
    }

   /* private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = fileName + ".xlsx";
            //fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }*/
}
