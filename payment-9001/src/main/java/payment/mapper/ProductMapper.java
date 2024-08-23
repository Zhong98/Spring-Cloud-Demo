package payment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void updateStockById(int id);
}
