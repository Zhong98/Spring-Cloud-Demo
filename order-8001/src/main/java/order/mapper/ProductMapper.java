package order.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int getProductStockById(int id);
    void setStock();
}
