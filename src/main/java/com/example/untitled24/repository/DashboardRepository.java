package com.example.untitled24.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository // 레포지토리 어노테이션
public class DashboardRepository {
    private final JdbcTemplate jdbcTemplate;

//    @Autowired // 생략되어있음
    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
                        // LocalDate : 자바의 시간데이터 / BigDecimal : 돈관련 데이터
    public void saveSale(LocalDate saleDate, int customerId, BigDecimal amount) {
        jdbcTemplate.update(
                "INSERT INTO sales (sale_date, customer_id, amount) VALUES (?, ?, ?)",
                saleDate, customerId, amount // 쿼리문 작성 (데이터베이스내의 이름과 같게 해야함)
        );
    }


}