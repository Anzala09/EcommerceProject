package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements IProductService {
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }
    public  GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
    public List<GenericProductDto> getAllProducts() {
        return null;
    }
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }
}
