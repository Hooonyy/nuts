package com.prac.almond.app.file;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ExcelUtil {
    public void uploadExcel(MultipartFile excelFile, String[] headerInfo) throws IOException, InvalidFormatException {
        OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        Sheet sheet = workbook.getSheetAt(0);

    }
        private String getValueToString (Cell cell){
            String value = "";
            if (cell == null) {
                value = "";
            } else {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_FORMULA:
                        value = cell.getCellFormula();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        value = cell.getNumericCellValue() + "";
                        break;
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue() + "";
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        value = cell.getErrorCellValue() + "";
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.getStringCellValue();
                }
            }
            return value;
        }
    }
