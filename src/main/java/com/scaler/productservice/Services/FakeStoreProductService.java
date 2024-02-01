package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import com.scaler.productservice.thirdpartyclients.productservice.Fakestore.FakeStoreProductDto;
import com.scaler.productservice.thirdpartyclients.productservice.Fakestore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
//                createProductRequestUrl, product, GenericProductDto.class);
//
//        return response.getBody();
          return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }
    public List<GenericProductDto > getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()){
            genericProductDtos.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
    return  genericProductDtos;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }
}
