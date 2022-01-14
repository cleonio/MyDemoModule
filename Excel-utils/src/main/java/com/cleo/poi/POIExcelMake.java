package com.cleo.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: com.cleo.poi-> POIExcelMake
 * @description:
 * @author: cleo
 * @createDate: 2022-01-11 20:36
 * @version: 1.0
 */
public class POIExcelMake<T> {

    private String name;
    private HSSFWorkbook hssfWorkbook;
    private HSSFSheet sheet;
    private Row crruentRow;//当前操作的行
    private int columnPosi;//当前行位置
    private int rowPosi;//当前列位置
    private int rowSize;//行的数量



    private int columnSize;//列的数量
    private List<T> data;

    /**
     * 这个map的第一个参数是行，第二个参数是是列中被占用了的位置，类似电影院买票时，有些被买了，有些没被买，用这个标记出来
     */
    private Map<Integer, Map<Integer,Integer>> excelMap;
    private HSSFCellStyle style;

    Map<Integer,Integer> maxWidth = new HashMap<Integer,Integer>();


    public POIExcelMake(String name) {
        this.name = name;

        columnPosi = 0;
        rowPosi = 0;
        rowSize = 0;
        columnSize = 0;

        this.hssfWorkbook = new HSSFWorkbook();
        this.sheet = this.hssfWorkbook.createSheet();
        this.excelMap = new HashMap();
        this.style = hssfWorkbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //style.setWrapText(true);

    }

    /**
     * 创建一行，也表示当前操作的行
     * @return
     */
    public Row createRow(short rowHeight){
        Row row = sheet.createRow(rowSize);
        row.setHeight(rowHeight);
        this.crruentRow = row;
        rowPosi = rowSize;  //当前行位置设为创建的行
        columnPosi = 0;
        /**
         * 在这里，通过excelMap进行过滤，确认当前的行的列的位置，因为我为了方便，先设置最大值是100
         */
        if(excelMap.containsKey(rowPosi)){
            Map<Integer, Integer> map = excelMap.get(rowPosi);
            for(int i=0;i<100;i++){
                if(!map.containsKey(i)){
                    columnPosi = i;
                    break;
                }
            }
        }
        columnSize = 0;
        rowSize++;
        return row;
    }

    /**
     * 创建一个长宽为1的cell
     * @return
     */
    public Cell createCell(){
        if(this.crruentRow==null)
            throw new RuntimeException("please create row first,there is no row for you to create cell");
        Cell cell = createCell(columnPosi);
        columnPosiForWard();
        columnSize++;
        return cell;
    }

    /**
     * 创建一个指定大小的cell
     * @param width
     * @param height
     * @return
     */
    public Cell createCell(int width,int height){
        int lastRow = rowPosi + height -1;
        int lastCol = columnPosi + width -1;
        sheet.addMergedRegion(new CellRangeAddress(rowPosi,lastRow, columnPosi, lastCol));
        dealMap(width,height);
        Cell cell = createCell(columnPosi);
        columnPosi =lastCol;
        columnPosiForWard();
        columnSize++;
        return cell;
    }

    private void dealMap(int width, int height) {
        // TODO Auto-generated method stub
        Integer perRowPosi = rowPosi;//获得当前行
        Integer perColumnPosi = columnPosi;//获得当前行的列位置
        for(int i=0;i<height-1;i++){
            perRowPosi++;//获得下一行
            if(!excelMap.containsKey(perRowPosi)){
                excelMap.put(perRowPosi, new HashMap<Integer,Integer>());
            }
            Map<Integer, Integer> rowMap = excelMap.get(perRowPosi);
            for(int j=0;j<width;j++){
                Integer col = perColumnPosi+j;
                if(!rowMap.containsKey(col)){
                    rowMap.put(col, col);
                }
            }
        }


    }



    public HSSFWorkbook getHssfWorkbook() {
        return hssfWorkbook;
    }

    private Cell createCell(int position){
        Cell cell = crruentRow.createCell(position);
        cell.setCellStyle(style);
        return cell;
    }

    private void columnPosiForWard(){
        columnPosi++;
        //如果包含当前行，获得该行，判断当前位置是否有被使用，如果往前移一格继续判断
        if(excelMap.containsKey(rowPosi)){
            Map<Integer, Integer> map = excelMap.get(rowPosi);
            if(map!=null){
                while(map.containsKey(columnPosi)){
                    columnPosi++;
                }
            }
        }
    }

    public void autoWith(){
        for (int i = 0; i < 13; i++) {
            //sheet.autoSizeColumn((short)i,true);
            sheet.setColumnWidth(i,4000);
        }
    }

    public HSSFSheet getSheet() {
        return sheet;
    }








}
