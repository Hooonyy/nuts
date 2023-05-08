package com.prac.almond.app.product.mapper;

import com.prac.almond.app.product.model.Product;
import com.prac.almond.app.product.model.ProductDto;
import com.prac.almond.app.product.model.ProductParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int saveProductData(ProductDto productDto);

    List<Product> findProductPaging(ProductParam productParam);

    int countProductList(ProductParam productParam);

    Product findProductInfo(int productSeq);

    int updateProductData(Product product);

    int deleteProductData(int productSeq);

    List<Product> findProductList();
}
