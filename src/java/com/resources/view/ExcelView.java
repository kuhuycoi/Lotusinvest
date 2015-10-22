package com.resources.view;

import com.resources.bean.ExcelFile;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ExcelFile em = (ExcelFile) model.get("myModel");

        response.setHeader("Content-Disposition", "attachment; filename=\"" + em.getFileName() + ".xls\"");

        HSSFSheet excelSheet = workbook.createSheet(em.getFileName());

        setExcelHeader(excelSheet, em.getTitles());
        setExcelRows(excelSheet, em.getContents());
    }

    public void setExcelHeader(HSSFSheet excelSheet, List titles) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            excelHeader.createCell(i).setCellValue(titles.get(i).toString());
        }
    }

    public void setExcelRows(HSSFSheet excelSheet, List contents) {
        int record = 1;
        for (Object rows : contents) {
            Object[] row = (Object[]) rows;
            HSSFRow excelRow = excelSheet.createRow(record++);
            int cnt = 0;
            for (Object value : row) {
                if (value == null) {
                    excelRow.createCell(cnt++).setCellValue("");
                } else {
                    excelRow.createCell(cnt++).setCellValue(value.toString());
                }
            }
        }
    }

}
