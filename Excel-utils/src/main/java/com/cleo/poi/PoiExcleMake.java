package com.cleo.poi;

/**
 * @className: com.cleo.poi-> PoiExcleMake
 * @description:
 * @author: cleo
 * @createDate: 2022-01-11 21:53
 * @version: 1.0
 */
public class PoiExcleMake {
    /**
     * 下载excel文件,内容使用MAP存放
     *
     * @param response
     * @param headName
     * @param tableHead
     * @param tableBody
     * @throws IOException
     */
   /* public static void downloadExcelMap(HttpServletResponse response, String headName, List<String> tableHead,
                                        List<Map<Object, Object>> tableBody) throws IOException {
        headName=StringUtils.replaceAllSpecial(headName);
        // 1:创建一个workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 创建样式
        HSSFCellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        style.setBorderTop((short) 1);
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setWrapText(true);

        // 设置合计样式
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        style1.setBorderTop((short) 1);
        style1.setBorderBottom((short) 1);
        style1.setBorderLeft((short) 1);
        style1.setBorderRight((short) 1);
        style.setWrapText(true);

        HSSFSheet sheet = (HSSFSheet) workbook.createSheet(headName);
        // 2：合并单元格，表头。并设置值
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, tableHead.size() - 1);
        sheet.addMergedRegion(cra);
        HSSFRow row = sheet.createRow(0);
        HSSFCell tableName = row.createCell(0);
        tableName.setCellStyle(style);
        tableName.setCellValue(headName);

        //存储最大列宽
        Map<Integer,Integer> maxWidth = new HashMap<Integer,Integer>();

        // 3：设置表head
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < tableHead.size(); i++) {
            Cell createCell = row1.createCell(i);
            createCell.setCellValue(tableHead.get(i));
            createCell.setCellStyle(style);
            maxWidth.put(i,createCell.getStringCellValue().getBytes().length  * 256 + 200);
        }
        // 4：表格内容
        for (int i = 0; i < tableBody.size(); i++) {
            HSSFRow rows = sheet.createRow(i + 2);
            int j = 0;
            for (Map.Entry<Object, Object> entry : tableBody.get(i).entrySet()) {
                HSSFCell createCell = rows.createCell(j);
                if(PropertyUtil.objectNotEmpty(entry.getValue())){
                    createCell.setCellValue(entry.getValue().toString());
                }else{
                    createCell.setCellValue("");
                }
                int length = createCell.getStringCellValue().getBytes().length  * 256 + 200;
                //这里把宽度最大限制到15000
                if (length>15000){
                    length = 15000;
                }
                maxWidth.put(j,Math.max(length,maxWidth.get(j)));
                j++;
                createCell.setCellStyle(style1);
            }
        }

        // 列宽自适应
        for (int i = 0; i < tableHead.size(); i++) {
            sheet.setColumnWidth(i,maxWidth.get(i));
        }

        // 5：设置头
        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(headName.getBytes("GB2312"), "ISO8859-1") + ".xls");
        // 6：设置头类型
        response.setContentType("application/vnd.ms-excel");

        // 7：写出
        OutputStream toClient = response.getOutputStream();
        workbook.write(toClient);
        toClient.flush();
        toClient.close();

    }
}*/
}