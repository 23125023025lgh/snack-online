package com.example.tradeservice.dto;

public class CreateOrderRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;
    private Long shopId;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, Long productId, Integer quantity, Long shopId) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}