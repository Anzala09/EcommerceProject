package com.scaler.productservice.thirdpartyclients.productservice.Fakestore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

}
