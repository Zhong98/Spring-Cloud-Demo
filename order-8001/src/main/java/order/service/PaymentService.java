package order.service;

import order.exception.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-payment-provider", fallback = FeignFallback.class)
public interface PaymentService {
    @GetMapping("/pay")
    String pay(@RequestParam(name = "productId") int productId);
}
