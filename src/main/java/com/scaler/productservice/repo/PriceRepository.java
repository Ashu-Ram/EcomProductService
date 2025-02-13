package com.scaler.productservice.repo;

import com.scaler.productservice.model.Price;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, UUID> {}
