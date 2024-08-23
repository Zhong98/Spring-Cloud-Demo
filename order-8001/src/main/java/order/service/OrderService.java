package order.service;

import entity.Order;

public interface OrderService {
    void saveOrder(Order order);
    Order getOrder(String username, int productId);
    boolean checkTime();
}
