package com.example.tradeservice.feign;

import com.example.tradeservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "store-service")
public interface ProductFeignClient {

    @GetMapping("/api/product/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}