package com.example.tradeservice.feign;

import com.example.tradeservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "store-service")
public interface ProductFeignClient {

    @GetMapping("/api/product/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    @PutMapping("/api/product/{id}/stock")
    ProductDTO decreaseStock(@PathVariable Long id, @RequestParam Integer quantity);
}