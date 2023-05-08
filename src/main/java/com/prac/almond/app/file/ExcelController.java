package com.prac.almond.app.file;

import com.prac.almond.app.file.model.ExcelData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {

    @GetMapping("/excel")
    public ModelAndView uploadExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("file/excel");
        return mav;
    }


    @PostMapping("/excel/read")
    @ResponseBody
    public ModelAndView readExcel(@RequestParam("file") MultipartFile file, ModelAndView mav)
            throws IOException { // 2

        List<ExcelData> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        System.out.println(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        XSSFDrawing drawing = (XSSFDrawing) worksheet.createDrawingPatriarch();

        List<XSSFPicture> xssfPictureList = new ArrayList<>();
        for (XSSFShape shape : drawing.getShapes()) {
            if (shape instanceof XSSFPicture) {
                XSSFPicture picture = (XSSFPicture) shape;

                if (picture.getPictureData() != null) {
                    XSSFPictureData xssfPictureData = picture.getPictureData();
                    ClientAnchor anchor = picture.getPreferredSize();
                    int row1 = anchor.getRow1();
                    int row2 = anchor.getRow2();
                    int col1 = anchor.getCol1();
                    int col2 = anchor.getCol2();
                    // Saving the file
                    String ext = xssfPictureData.suggestFileExtension();
                    xssfPictureList.add(picture);
                }
            }
        }
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows()*2-1; i+= 2) { // 6
            System.out.println(worksheet.getPhysicalNumberOfRows());
            Row row = worksheet.getRow(i);
            ExcelData data = new ExcelData();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmSS");
            Date time = new Date();
            String today = simpleDateFormat.format(time);
                data.setProductName(row.getCell(0).getStringCellValue());
                for(int j=0; j<xssfPictureList.size(); j++){
                    ClientAnchor anchor = xssfPictureList.get(j).getPreferredSize();
                    int row1 = anchor.getRow1();
                    if(row1 == i){
                        //picture -> byte[]
                        byte[] imgData = xssfPictureList.get(j).getPictureData().getData();
                        //byte[] -> file
                        Path target = Paths.get("D:\\"+File.separator+"upload"+File.separator+"images"+File.separator+i+"_"+today+".jpg");
                        InputStream is = new ByteArrayInputStream(imgData);
                        BufferedImage newBi = ImageIO.read(is);
                        ImageIO.write(newBi, "jpeg", target.toFile());
                        //file -> 경로에
                        data.setProductImgpath(File.separator+"images"+File.separator+i+".jpg");
                    }
                }
                data.setProductPrice((long) row.getCell(2).getNumericCellValue());
                data.setProductContents(row.getCell(3).getStringCellValue());
                data.setProductDiscountCode(row.getCell(4).getStringCellValue());
                data.setDiscountPrice((long) row.getCell(5).getNumericCellValue());
                dataList.add(data);
        }
        mav.addObject("datas", dataList); // 5
        mav.setViewName("file/excelList");
        return mav;
    }
}
