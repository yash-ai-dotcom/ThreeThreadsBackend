package com.Service;

import com.Entity.ArticleColor;
import com.Entity.Inventory;
import com.Entity.Order;
import com.Entity.OrderItem;
import com.Repository.InventoryRepository;
import com.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order placeOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            Inventory inv = inventoryRepository.findById(item.getInventoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Article not found: " + item.getArticleNo()));

            ArticleColor targetColor = inv.getColors().stream()
                    .filter(c -> c.getColorName().equalsIgnoreCase(item.getColorName()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Color " + item.getColorName() + " not found"));

            // 1. Deduct ordered full sets from Inventory
            if (targetColor.getSetTotal() < item.getSetsOrdered()) {
                throw new IllegalArgumentException("Not enough set stock for article: " + inv.getArticleNo());
            }
            targetColor.setSetTotal(targetColor.getSetTotal() - item.getSetsOrdered());

            // 2. Handle Excluded Sizes Return to Loose Stock
            if (item.getExcludedSizes() != null && !item.getExcludedSizes().trim().isEmpty()) {
                List<String> excludedList = Arrays.asList(item.getExcludedSizes().toUpperCase().split(","));
                int returnedQuantity = item.getSetsOrdered(); // 1 per set ordered

                for (String sz : excludedList) {
                    switch (sz.trim()) {
                        case "S":
                            targetColor.setSizeS(targetColor.getSizeS() + returnedQuantity);
                            break;
                        case "M":
                            targetColor.setSizeM(targetColor.getSizeM() + returnedQuantity);
                            break;
                        case "L":
                            targetColor.setSizeL(targetColor.getSizeL() + returnedQuantity);
                            break;
                        case "XL":
                            targetColor.setSizeXL(targetColor.getSizeXL() + returnedQuantity);
                            break;
                        case "XXL":
                            targetColor.setSizeXXL(targetColor.getSizeXXL() + returnedQuantity);
                            break;
                    }
                }
            }
            inventoryRepository.save(inv);
        }

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}