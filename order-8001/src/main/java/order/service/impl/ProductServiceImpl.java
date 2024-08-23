package order.service.impl;

import order.mapper.ProductMapper;
import order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public int getStock(int productId) {
        return productMapper.getProductStockById(productId);
    }

    @Override
    @Scheduled(cron = "0 0 11 ? * MON-FRI")
    public void setStock() {
        productMapper.setStock();
    }
}
