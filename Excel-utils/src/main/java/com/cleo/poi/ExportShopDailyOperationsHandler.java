package com.cleo.poi;

import com.cleo.easyexcel.ShopDailyOperations;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;

/**
 * @className: com.cleo.poi-> ExportShopDailyOperationsHandler
 * @description:
 * @author: cleo
 * @createDate: 2022-01-12 14:06
 * @version: 1.0
 */
public class ExportShopDailyOperationsHandler {

    private List<ShopDailyOperations> list;
    private XSSFCellStyle xssfCellStyle;
    private XSSFCellStyle xssfcellstyletwo;
    private XSSFCellStyle headerStyle;
    private XSSFCellStyle titleStyleOne;
    private XSSFCellStyle titleStyleTwo;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;

    public ExportShopDailyOperationsHandler (List<ShopDailyOperations> list){
        this. wb = new XSSFWorkbook();
        this.xssfCellStyle = XSSFWorkbookStyleUtil.getXSSFCellStyle(wb);
        this.xssfcellstyletwo = XSSFWorkbookStyleUtil.getXSSFCellStyleTwo(wb);
        this.headerStyle = XSSFWorkbookStyleUtil.getHeaderStyleOne(wb);
        this.titleStyleOne = XSSFWorkbookStyleUtil.getTitleStyleOne(wb);
        this.titleStyleTwo = XSSFWorkbookStyleUtil.getTitleStyleTwo(wb);
        this.list = list;
        this.sheet = wb.createSheet("Sheet0");
    }

    public void createHeader(String date){

        String[] headers = {"商品名称", "销售数量", "销售单价", "含税销售收入", "不含税销售收入"
                , "销项税率", "销项税", "成本含税单价", "含税成本", "不含税成本"
                , "进项税率", "进项税", "优惠金额"};

        XSSFRow headerRow0 = sheet.createRow(0);
        XSSFCell cellR0 = headerRow0.createCell(0);
        cellR0.setCellValue("郑铁易行—全球购营业日报明细表");
        cellR0.setCellStyle(xssfCellStyle);
        CellRangeAddress regionRow0 = new CellRangeAddress(0, 0, 0, headers.length-1);
        sheet.addMergedRegion(regionRow0);

        XSSFRow headerRow1 = sheet.createRow(1);
        XSSFCell cellR1C0 = headerRow1.createCell(0);
        cellR1C0.setCellValue("日期");
        cellR1C0.setCellStyle(xssfCellStyle);
        XSSFCell cellR1C1 = headerRow1.createCell(1);
        cellR1C1.setCellValue(date);
        cellR1C1.setCellStyle(xssfCellStyle);

        CellRangeAddress regionRow1 = new CellRangeAddress(1, 1, 1, headers.length-1);
        sheet.addMergedRegion(regionRow1);

        XSSFRow headerRow2 = sheet.createRow(2);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow2.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

    }

    public void filldata(){
        for (ShopDailyOperations operations : list) {
            int lastRowNum = sheet.getLastRowNum();
            XSSFRow row = sheet.createRow(lastRowNum+1);
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
            row.createCell(10).setCellValue(operations.getBuyTaxRate().toString());
            row.createCell(11).setCellValue(operations.getBuyTax().toPlainString());
            row.createCell(12).setCellValue(operations.getReduceAmount().toPlainString());
        }
    }

    public void createFeetHeader(){

        XSSFRow rowsxf = sheet.createRow(sheet.getLastRowNum()+1);
        rowsxf.createCell(0).setCellValue("手续费");

        XSSFRow rowxj = sheet.createRow(sheet.getLastRowNum()+1);
        rowxj.createCell(0).setCellValue("小计");

    }

    public XSSFWorkbook getWb() {
        return wb;
    }
}
