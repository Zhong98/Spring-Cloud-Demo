package order.mapper;

import entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int saveOrder(Order order);
}
