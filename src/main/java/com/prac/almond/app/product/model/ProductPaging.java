package com.prac.almond.app.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductPaging {
    private List<Product> productList;

    private int page = 1;
    private int listSize;
    private int pageSize = 5;
}
