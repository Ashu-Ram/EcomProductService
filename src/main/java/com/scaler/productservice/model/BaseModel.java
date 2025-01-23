package com.scaler.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;

}
