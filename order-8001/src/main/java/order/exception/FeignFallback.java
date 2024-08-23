package order.exception;

import order.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements PaymentService {
    @Override
    public String pay(int productId) {
        return "Exception occurred, please try again later";
    }
}
