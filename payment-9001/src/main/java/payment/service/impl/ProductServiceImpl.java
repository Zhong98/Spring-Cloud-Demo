package payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.mapper.ProductMapper;
import payment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void updateStock(int productId) {
        productMapper.updateStockById(productId);
    }
}
