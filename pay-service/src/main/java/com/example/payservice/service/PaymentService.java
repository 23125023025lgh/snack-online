package com.example.payservice.service;

import com.example.payservice.entity.Payment;
import com.example.payservice.feign.TradeFeignClient;
import com.example.payservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TradeFeignClient tradeFeignClient;

    public Payment createPayment(Long orderId, Long userId, BigDecimal payAmount) {
        Payment payment = new Payment(orderId, userId, payAmount, "支付成功");
        Payment savedPayment = paymentRepository.save(payment);

        tradeFeignClient.updateOrderStatus(orderId, "已支付");

        return savedPayment;
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoSuchElementException("支付记录不存在"));
    }
}