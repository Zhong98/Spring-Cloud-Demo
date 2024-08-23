package order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import order.exception.BlockHandler;
import order.exception.FallbackHandler;
import order.service.PaymentService;
import order.service.OrderService;
import order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class OrderController {
    /*@Autowired
    RestTemplate restTemplate;*/

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Qualifier("order.service.PaymentService")
    @Autowired
    PaymentService paymentService;

    @Value("${sale.status}")
    private int saleStatus;

    @GetMapping("/seckill")
    @SentinelResource(value = "seckill",
            blockHandlerClass = BlockHandler.class, blockHandler = "handleException",
            fallbackClass = FallbackHandler.class, fallback = "fallback")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String seckill(String username, String goodId) {
        if (saleStatus == 1) {
            if (orderService.checkTime()) {
                if (username == null || goodId == null) {
                    return "Username or goodId is null";
                }
                int productId;
                try {
                    productId = Integer.parseInt(goodId);
                } catch (NumberFormatException e) {
                    return "goodId is not a number";
                }
                int stock = productService.getStock(productId);
                if (stock <= 0) {
                    return "Sorry, the product has already sold out.";
                }
                Order order = orderService.getOrder(username, productId);
                paymentService.pay(productId);
                orderService.saveOrder(order);
                //restTemplate.getForObject("http://nacos-payment-provider/pay?productId=" + productId, String.class);
                return "Order created successfully, please waiting for delivery";
            }
        }
        return "The flash sale hasn't started yet.";
    }
}
