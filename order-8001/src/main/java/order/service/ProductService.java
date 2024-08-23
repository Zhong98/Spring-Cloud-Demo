package order.service;

public interface ProductService {
    int getStock(int productId);
    void setStock();
}
