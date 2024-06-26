package com.scaler.productservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private double price;

}
