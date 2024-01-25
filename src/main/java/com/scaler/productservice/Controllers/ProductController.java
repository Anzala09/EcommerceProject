package com.scaler.productservice.Controllers;

import com.scaler.productservice.Dtos.ExceptionDto;
import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.Services.IProductService;
import com.scaler.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
       return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.OK   // 200
        );
    }

    @PostMapping
    public GenericProductDto  createProduct(@RequestBody GenericProductDto product) {
    return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProductById() {

    }
}
