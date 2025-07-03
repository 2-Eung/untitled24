package com.example.untitled24.model;


import java.math.BigDecimal;
import java.time.LocalDate;

// Model 은 필요한 데이터 필드 만들고 그에대한 게터,세터 를 정하는게 거의 다 이다.
public class DailySales {
    private LocalDate saleDate;
    private BigDecimal totalAmount;
    private int orderCount;

    public DailySales() {} // 빈생성자를 만든 이유 : 22/23 일자꺼 확인

    public DailySales(LocalDate saleDate, BigDecimal totalAmount, int orderCount) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.orderCount = orderCount;
    }
    public LocalDate getSaleDate() {
        return saleDate;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public int getOrderCount() {
        return orderCount;
    }
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}