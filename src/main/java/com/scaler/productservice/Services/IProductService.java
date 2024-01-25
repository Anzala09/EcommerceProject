package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.Models.Product;
import com.scaler.productservice.exceptions.NotFoundException;

import java.util.List;

public interface IProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(Long id);

}
