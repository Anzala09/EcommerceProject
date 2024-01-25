package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.FakeStoreProductDto;
import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;
@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

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
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, GenericProductDto.class);

        return response.getBody();
    }
    public List<GenericProductDto > getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
       // ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.getForEntity(
        //getAllProductsRequestUrl, List<FakeStoreProductDto.class>); doesnt works because of generics and erasure at run time

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                getAllProductsRequestUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> answer = new  ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()) {

            answer.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }

        return answer;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build(); //creates rest template obj
       ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
               getProductRequestUrl, FakeStoreProductDto.class, id);

       FakeStoreProductDto fakeStoreProductDto = response.getBody();
       //  response.getStatusCode()
        if(fakeStoreProductDto == null) {
     throw new NotFoundException("Product with id " + id + " not found");
        }
        return conver tFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);

    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(
                deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }
}
