package com.scaler.productservice.Services;

import com.scaler.productservice.Dtos.FakeStoreProductDto;
import com.scaler.productservice.Dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    
    // testing changes
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, GenericProductDto.class);

        return response.getBody();
    }
    public List<GenericProductDto > getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.getForEntity(
                getAllProductsRequestUrl, List<FakeStoreProductDto.class>);

        List<GenericProductDto> answer = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()) {
            GenericProductDto product = new GenericProductDto();
            product.setImage(fakeStoreProductDto.getImage());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setCategory(fakeStoreProductDto.getCategory());
            answer.add(product);
        }

        return answer;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build(); //creates rest template obj
       ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
               getProductRequestUrl, FakeStoreProductDto.class, id);

       FakeStoreProductDto fakeStoreProductDto = response.getBody();
       GenericProductDto product = new GenericProductDto();
       product.setImage(fakeStoreProductDto.getImage());
       product.setDescription(fakeStoreProductDto.getDescription());
       product.setPrice(fakeStoreProductDto.getPrice());
       product.setTitle(fakeStoreProductDto.getTitle());
       product.setCategory(fakeStoreProductDto.getCategory());
      //  response.getStatusCode()
        return product;
    }

}
