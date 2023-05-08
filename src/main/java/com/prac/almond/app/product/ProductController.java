package com.prac.almond.app.product;

import com.prac.almond.app.product.model.Product;
import com.prac.almond.app.product.model.ProductDto;
import com.prac.almond.app.product.model.ProductPaging;
import com.prac.almond.app.product.model.ProductParam;
import com.prac.almond.app.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public ModelAndView productList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/productList");
        return mav;
    }

    @GetMapping("/product/productList")
    @ResponseBody
    public Object productListData(ProductParam productParam) {
        ProductPaging result = productService.findProductPaging(productParam);
        return result;
    }

    @GetMapping("/product/insert")
    public ModelAndView insertProduct() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/registerProduct");
        return mav;
    }

    @PostMapping("/product/save")
    @ResponseBody
    public String saveProductData(ProductDto productDto) {
        String result = productService.saveProductData(productDto);
        return result;
    }

    @GetMapping("/product/productInfo/{productSeq}")
    public ModelAndView userInfo(@PathVariable("productSeq") int productSeq) {
        ModelAndView modelAndView = new ModelAndView();
        Product productInfo = productService.findProductInfo(productSeq);
        modelAndView.addObject("productInfo", productInfo);
        modelAndView.setViewName("product/productInfo");
        return modelAndView;
    }

    //상품정보 수정
    @PutMapping("/product/update")
    @ResponseBody
    public String updateProductData(ProductDto productDto) {
        String updateProductData = productService.updateProductData(productDto);
        System.out.println(updateProductData);
        return updateProductData;
    }

    //상품정보 삭제
    @DeleteMapping("/product/delete")
    @ResponseBody
    public String deleteProductData(int productSeq) {
        String deleteProductData = productService.deleteProductData(productSeq);
        return deleteProductData;
    }

    @GetMapping("/excelDownload")
    public void downloadExcel(HttpServletResponse response) throws IOException {

        XSSFWorkbook workbook = productService.excelWrite();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();
        String today = simpleDateFormat.format(time);

        //다운로드
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=productlist"+"_"+today+".xlsx");

        workbook.write(response.getOutputStream());

    }

}
