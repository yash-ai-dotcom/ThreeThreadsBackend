package com.Service;

import com.DTO.ArticlePerformanceDTO;
import com.DTO.DashboardMetricsDTO;
import com.Entity.Expense;
import com.Entity.Inventory;
import com.Entity.Order;
import com.Entity.OrderItem;
import com.Repository.ExpenseRepository;
import com.Repository.InventoryRepository;
import com.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public DashboardMetricsDTO getPerformanceMetrics() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<Order> orderList = orderRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        DashboardMetricsDTO dto = new DashboardMetricsDTO();
        Map<String, ArticlePerformanceDTO> performanceMap = new HashMap<>();

        int totalPiecesInStock = 0;
        double stockCostValuation = 0.0;
        double stockSellValuation = 0.0;

        // 1. Process Inventory Stock Metrics
        for (Inventory inv : inventoryList) {
            int qty = inv.getGrandTotal() != null ? inv.getGrandTotal() : 0;
            double buyPrice = inv.getCostPerPiece() != null ? inv.getCostPerPiece() : 0.0;
            double sellPrice = inv.getSellingCostPerPiece() != null ? inv.getSellingCostPerPiece() : 0.0;

            totalPiecesInStock += qty;
            stockCostValuation += (qty * buyPrice);
            stockSellValuation += (qty * sellPrice);

            ArticlePerformanceDTO artDto = new ArticlePerformanceDTO();
            artDto.setInventoryId(inv.getId());
            artDto.setArticleNo(inv.getArticleNo());
            artDto.setCategory(inv.getCategory());
            artDto.setBrand(inv.getBrand());
            artDto.setInStockQty(qty);
            artDto.setCostPerPiece(buyPrice);
            artDto.setSellingCostPerPiece(sellPrice);
            artDto.setTotalStockBuyCost(qty * buyPrice);
            artDto.setTotalStockSellValuation(qty * sellPrice);

            performanceMap.put(inv.getArticleNo(), artDto);
        }

        // 2. Process Order Sales Metrics
        int totalUnitsSold = 0;
        double totalRevenue = 0.0;

        for (Order order : orderList) {
            if ("CANCELLED".equalsIgnoreCase(order.getStatus())) continue;

            totalRevenue += (order.getTotalAmount() != null ? order.getTotalAmount() : 0.0);

            for (OrderItem item : order.getItems()) {
                int pieces = item.getTotalPiecesOrdered() != null ? item.getTotalPiecesOrdered() : 0;
                totalUnitsSold += pieces;

                if (performanceMap.containsKey(item.getArticleNo())) {
                    ArticlePerformanceDTO artDto = performanceMap.get(item.getArticleNo());
                    artDto.setUnitsSold(artDto.getUnitsSold() + pieces);
                    artDto.setTotalRevenueGenerated(artDto.getTotalRevenueGenerated() + (item.getItemTotal() != null ? item.getItemTotal() : 0.0));
                }
            }
        }

        // 3. Process Operational Expenses
        double totalExpenses = expenseList.stream()
                .mapToDouble(e -> e.getAmount() != null ? e.getAmount() : 0.0)
                .sum();

        // 4. Assemble Final Dashboard DTO
        dto.setTotalStockPieces(totalPiecesInStock);
        dto.setTotalStockCostValuation(stockCostValuation);
        dto.setTotalStockSellingValuation(stockSellValuation);
        dto.setPotentialGrossMargin(stockSellValuation - stockCostValuation);

        dto.setTotalOrdersPlaced((long) orderList.size());
        dto.setTotalUnitsSold(totalUnitsSold);
        dto.setTotalRevenue(totalRevenue);

        dto.setTotalOperationalExpenses(totalExpenses);
        dto.setNetProfitOrLoss(totalRevenue - totalExpenses);

        dto.setArticlePerformances(new ArrayList<>(performanceMap.values()));

        return dto;
    }
}