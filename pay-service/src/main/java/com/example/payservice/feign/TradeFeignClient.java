package com.example.payservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "trade-service")
public interface TradeFeignClient {

    @PutMapping("/api/order/{orderId}/status")
    void updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") String status);
}