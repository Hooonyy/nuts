package com.prac.almond.app.product.service;

import com.prac.almond.app.product.mapper.ProductMapper;
import com.prac.almond.app.product.model.Product;
import com.prac.almond.app.product.model.ProductDto;
import com.prac.almond.app.product.model.ProductPaging;
import com.prac.almond.app.product.model.ProductParam;
import com.prac.almond.app.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public String saveProductData(ProductDto productDto) {
        if (productDto.getProductDiscountCode().equals("percent")) {
            Long proDiscount = productDto.getProductDiscount();
            if (proDiscount < 1 || proDiscount >= 100) {
                System.out.println("할인 범위를 초과했습니다.");
                return "할인 범위를 초과했습니다.";
            }
        }
        if (productDto.getProductDiscountCode().equals("price")) {
            if (productDto.getProductPrice() < productDto.getProductDiscount()) {
                System.out.println("할인가가 원가보다 높습니다.");
                return "할인가가 원가보다 높습니다.";
            }
        }

        if (productDto.getMultipartFile() != null) {
            try {
                String filePath = UploadUtil.uploadImage("product", productDto.getMultipartFile());
                productDto.setFileUploadYn("Y");
                productDto.setProductImgPath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            productDto.setFileUploadYn("N");
        }
        int result = productMapper.saveProductData(productDto);
        if (result > 0) {
            return "success";
        }
        return "fail";
    }

    public ProductPaging findProductPaging(ProductParam productParam) {

        List<Product> list = productMapper.findProductPaging(productParam);

        ProductPaging productPaging = new ProductPaging();
        productPaging.setProductList(list);

        int countProductList = productMapper.countProductList(productParam);
        productPaging.setListSize(countProductList);
        productPaging.setPage(productParam.getPageNum());
        return productPaging;
    }

    public Product findProductInfo(int productSeq) {
        Product productInfoData = productMapper.findProductInfo(productSeq);
        return productInfoData;
    }

    public String updateProductData(ProductDto productDto) {

        if (productDto.getProductDiscountCode().equals("percent")) {
            Long proDiscount = productDto.getProductDiscount();
            if (proDiscount < 1 || proDiscount >= 100) {
                System.out.println("할인 범위를 초과했습니다.");
                return "할인 범위를 초과했습니다.";
            }
        }
        if (productDto.getProductDiscountCode().equals("price")) {
            if (productDto.getProductPrice() > productDto.getProductDiscount()) ;
            {
                System.out.println("할인가가 원가보다 높습니다.");
                return "할인가가 원가보다 높습니다.";
            }
        }

        Product product = new Product();
        
        product.setProductSeq(productDto.getProductSeq());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductContents(productDto.getProductContents());
        product.setProductEtc(productDto.getProductEtc());
        product.setProductDiscountCode(productDto.getProductDiscountCode());
        product.setProductDiscount(productDto.getProductDiscount());
        product.setProductIsdel(productDto.getProductIsdel());

        if (productDto.getMultipartFile() != null) {
            try {
                String filePath = UploadUtil.uploadImage("product", productDto.getMultipartFile());
                product.setFileUploadYn("Y");
                product.setProductImgpath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            product.setFileUploadYn("N");
        }
        int updateProductData = productMapper.updateProductData(product);
        if (updateProductData == 1) {
            return "success";
        }
        return "fail";
    }

    public String deleteProductData(int productSeq) {
        int deleteProduct = productMapper.deleteProductData(productSeq);
        if (deleteProduct == 1) {
            return "success";
        }
        return "fail";
    }

    public List<Product> findProductList() {
        List<Product> productList = productMapper.findProductList();
        return productList;
    }

    public XSSFWorkbook excelWrite() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFCellStyle cs = workbook.createCellStyle();

        //정렬
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        //테두리 설정
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);


        XSSFSheet sheet = workbook.createSheet("게시판글들");
        int rowNo = 0;
        //헤더
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("상품명");
        headerRow.createCell(1).setCellValue("제품사진");
        headerRow.createCell(2).setCellValue("가격");
        headerRow.createCell(3).setCellValue("비고");
        headerRow.createCell(4).setCellValue("할인코드");
        headerRow.createCell(5).setCellValue("할인률(가격)");

        List<Product> list = this.findProductList();
        System.out.println(list.size());
        for (Product product : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(product.getProductName());
            //2007 이상은 XSSF .xlsx확장자
            String filepath = /*"D:\\upload\\images\\user\\apple.jpg";*/
                    "D:\\upload" + product.getProductImgpath() + "\"";
            filepath = filepath.replace("\"", "\\");
            InputStream is = new FileInputStream(filepath);
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureindex = workbook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);

            //위치잡기
            XSSFClientAnchor anchor = new XSSFClientAnchor();
            anchor.setCol1(1);
            rowNo--;
            anchor.setRow1(rowNo++);
            anchor.setRow2(rowNo++);
            //그리기
            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            //위치에 이미지 그리기
            XSSFPicture pic = drawing.createPicture(anchor, pictureindex);
            //사진 배율
            pic.resize(0.15);
            for (int j = 1; j<list.size()*2;j++){
                for(int i = 0; i<6; i++){
                    sheet.addMergedRegion(new CellRangeAddress(j, j+1, i, i));
                }
            }

            /*row.createCell(1).setCellValue(product.getProductImgpath());*/
            row.createCell(2).setCellValue(product.getProductPrice());
            row.createCell(3).setCellValue(product.getProductEtc());
            row.createCell(4).setCellValue(product.getProductDiscountCode());
            row.createCell(5).setCellValue(product.getProductDiscount());
        }
        return workbook;
    }
}
