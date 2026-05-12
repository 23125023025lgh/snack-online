package com.example.storeservice.repository;

import com.example.storeservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShopId(Long shopId);

    List<Product> findByNameContaining(String name);

    List<Product> findByShopIdAndNameContaining(Long shopId, String name);

    boolean existsByName(String name);
}