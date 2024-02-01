package com.scaler.productservice.thirdpartyclients.productservice.Fakestore;

import com.scaler.productservice.Dtos.GenericProductDto;
import com.scaler.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// wrapper overFakeStore API
@Service
public class FakeStoreProductServiceClient  {
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductApiPath;
    private String getProductRequestUrl = fakeStoreApiUrl + fakeStoreProductApiPath + "/{id}";
    private String createProductRequestUrl = fakeStoreApiUrl + fakeStoreProductApiPath;
    private String getAllProductsRequestUrl = fakeStoreApiUrl + fakeStoreProductApiPath;
    private String deleteProductRequestUrl = fakeStoreApiUrl + fakeStoreProductApiPath + "/{id}";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
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

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, FakeStoreProductDto.class);

        return response.getBody();
    }
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.getForEntity(
        //getAllProductsRequestUrl, List<FakeStoreProductDto.class>); doesnt works because of generics and erasure at run time

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                getAllProductsRequestUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> answer = new ArrayList<>();

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build(); //creates rest template obj
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                getProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        //  response.getStatusCode()
        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return fakeStoreProductDto;

    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(
                deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }
}
