package com.scaler.productservice.inheritancerepresentation.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mentor extends User {
    private Double avgRating;
}
