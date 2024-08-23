package payment.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.exception.BlockHandler;
import payment.exception.FallbackHandler;
import payment.service.ProductService;

@RestController
public class PayController {

    @Autowired
    ProductService productService;

    @GetMapping("/pay")
    @SentinelResource(value = "pay", blockHandlerClass = BlockHandler.class, blockHandler = "handleException",
            fallbackClass = FallbackHandler.class, fallback = "fallback")
    public String pay(int productId) {
        productService.updateStock(productId);
        return "success";
    }
}
