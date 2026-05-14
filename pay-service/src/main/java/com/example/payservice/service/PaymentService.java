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
        Payment payment = new Payment(orderId, userId, payAmount, Payment.STATUS_PROCESSING);
        Payment savedPayment = paymentRepository.save(payment);

        try {
            Thread.sleep(1500);

            savedPayment.setStatus(Payment.STATUS_SUCCESS);
            paymentRepository.save(savedPayment);

            tradeFeignClient.updateOrderStatus(orderId, "已支付");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            savedPayment.setStatus(Payment.STATUS_FAILED);
            paymentRepository.save(savedPayment);
            throw new RuntimeException("支付过程被中断", e);
        } catch (Exception e) {
            savedPayment.setStatus(Payment.STATUS_FAILED);
            paymentRepository.save(savedPayment);
            throw new RuntimeException("支付失败", e);
        }

        return savedPayment;
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoSuchElementException("支付记录不存在"));
    }
}