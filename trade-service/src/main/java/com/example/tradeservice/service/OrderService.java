package com.example.tradeservice.service;

import com.example.tradeservice.dto.CreateOrderRequest;
import com.example.tradeservice.dto.ProductDTO;
import com.example.tradeservice.entity.Order;
import com.example.tradeservice.feign.ProductFeignClient;
import com.example.tradeservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    public Order createOrder(CreateOrderRequest request) {
        ProductDTO product = productFeignClient.getProductById(request.getProductId());
        
        if (product == null) {
            throw new NoSuchElementException("商品不存在");
        }

        if (product.getStock() < request.getQuantity()) {
            throw new IllegalArgumentException("库存不足");
        }

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order(
                request.getUserId(),
                product.getId(),
                product.getName(),
                request.getQuantity(),
                totalPrice,
                "待支付",
                request.getShopId()
        );

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("订单不存在"));
    }
}