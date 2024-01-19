package com.scaler.productservice.Controllers;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.Services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    public ProductController(@Qualifier("fakeStoreProductService") IProductService productService) {
        this.productService = productService;
    }
    // GET /products{}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteProductById() {

    }
    @PostMapping
    public GenericProductDto  createProduct(@RequestBody GenericProductDto product) {
    return productService.createProduct(product);
    }
    @PutMapping("/{id}")
    public void updateProductById() {

    }
}
