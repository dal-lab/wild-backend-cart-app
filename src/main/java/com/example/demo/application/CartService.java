package com.example.demo.application;

import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final LineItemDAO lineItemDAO;
    private final ProductDAO productDAO;

    public CartService(LineItemDAO lineItemDAO, ProductDAO productDAO) {
        this.lineItemDAO = lineItemDAO;
        this.productDAO = productDAO;
    }

    public Cart getCart() {
        List<LineItem> lineItems = lineItemDAO.findAll();
        lineItems.forEach(lineItem -> {
            Product product = productDAO.find(lineItem.getProductId());
            int price = product.getPrice();
            int quantity = lineItem.getQuantity();
            lineItem.setTotalPrice(price * quantity);
            lineItem.setProductName(product.getName());
            lineItem.setUnitPrice(price);

        });
        return new Cart(
                lineItems,
                lineItems.stream().mapToInt(LineItem::getTotalPrice).sum()
        );
    }

    public void addProduct(String productId, int quantity) {
        List<LineItem> lineItems = lineItemDAO.findAll();
        LineItem lineItem = lineItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
        if (lineItem == null) {
            lineItem = new LineItem(productId, quantity);
            lineItemDAO.add(lineItem);
            return;
        }
        lineItem.setQuantity(lineItem.getQuantity() + quantity);
        lineItemDAO.update(lineItem);
    }
}
