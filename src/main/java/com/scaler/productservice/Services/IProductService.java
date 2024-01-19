package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.Models.Product;

import java.util.List;

public interface IProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);
    List<GenericProductDto> getAllProducts();
}
