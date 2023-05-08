package com.prac.almond.app.file.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelData {

    private String productName;
    private String productImgpath;
    private Long productPrice;
    private String productContents;
    private String productDiscountCode;
    private Long discountPrice;
}
