package com.scaler.productservice.Models;

import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Entity
public class Category extends BaseModel {
   private String name;
}
