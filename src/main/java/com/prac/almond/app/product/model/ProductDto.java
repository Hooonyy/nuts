package com.prac.almond.app.product.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ProductDto {
    private Long productSeq;
    private String productName;
    private String productImgPath;
    private Long productPrice;
    private String productContents;
    private String productEtc;
    private String productDiscountCode;
    private Long productDiscount;
    private String productIsdel;
    private String FileUploadYn;
    private MultipartFile multipartFile;

}
