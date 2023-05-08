package com.prac.almond.app.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long productSeq;
    private String productName;
    private String productImgpath;
    private Long productPrice;
    private String productContents;
    private String productEtc;
    private String productDiscountCode;
    private Long productDiscount;
    private String productIsdel;
    private String fileUploadYn;
    private Double discountPrice;
}
