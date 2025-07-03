package com.example.untitled24.repository;

import com.example.untitled24.model.CustomerRank;
import com.example.untitled24.model.DailySales;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository // 레포지토리 어노테이션
public class DashboardRepository {
    private final JdbcTemplate jdbcTemplate;

//    @Autowired // 생략되어있음
    public DashboardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // DailySales 모델 과 관련이 있음 22/23 일자꺼 에도 썻으니 확인해보자
    private final RowMapper<DailySales> dailySalesRowMapper = (resultSet, rowNum) ->
            new DailySales(
                    resultSet.getDate("sale_date").toLocalDate(), // 날짜 데이터로 바꿔준다.
                    resultSet.getBigDecimal("total_amount"),
                    resultSet.getInt("order_count")
            );

    // CustomerRank 모델 과 관련이 있음 22/23 일자꺼 에도 썻으니 확인해보자
    private final RowMapper<CustomerRank> customerRankRowMapper = (resultSet, rowNum) ->
            new CustomerRank(
                    resultSet.getInt("customer_id"),
                    resultSet.getBigDecimal("total_spent"),
                    resultSet.getInt("rank")
            );

    // DailySales 데이터베이스 와 관련이 있음 22/23 일자꺼 에도 썻으니 확인해보자
    public List<DailySales> findDailySales() {
        return jdbcTemplate.query(
                "SELECT * FROM daily_sales", // 테이블이 아니라 뷰 를 꺼내오는것
                dailySalesRowMapper // 뷰 는 테이블을 재정리해서 만든 가상의 테이블이 뷰 이다 (뷰 만들때 계산을 넣어서 계산까지해준다!!!!)
        );
    }

    public  List<CustomerRank> findCustomerRankings() { // total_spent 를 기준으로 내림차순 정렬하겠다.
        String sql = """
                       SELECT customer_id,
                       total_spent,
                       RANK() OVER (ORDER BY total_spent DESC) AS rank
                       FROM customer_spending
                     """;
        return jdbcTemplate.query(sql, customerRankRowMapper); // 직접 넣으면 오류가 발생할 수 있어서 sql 로 따로 빼서 만듬
    }
                        // LocalDate : 자바의 시간데이터 / BigDecimal : 돈관련 데이터
    public void saveSale(LocalDate saleDate, int customerId, BigDecimal amount) {
        jdbcTemplate.update(
                "INSERT INTO sales (sale_date, customer_id, amount) VALUES (?, ?, ?)",
                saleDate, customerId, amount // 쿼리문 작성 (데이터베이스내의 이름과 같게 해야함)
        );
    }


}