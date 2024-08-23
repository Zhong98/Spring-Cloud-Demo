package order.service.impl;

import entity.Order;
import order.mapper.OrderMapper;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
    }

    @Override
    public Order getOrder(String username, int productId) {
        Order order = new Order();
        int id = 10000000 + new Random().nextInt(90000000);
        order.setId(id);
        order.setUsername(username);
        order.setProductId(productId);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        order.setCreateTime(createTime);
        return order;
    }

    @Override
    public boolean checkTime() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int hour = now.getHour();
        int minute = now.getMinute();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && hour == 11 && minute <= 10;
    }
}
