package com.example.untitled24.controller;

import com.example.untitled24.repository.DashboardRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/sales") // 컨트롤러 전체에 대한 경로 이하 GetMapping 경로의 앞에 존재해야만 한다
public class SalesController {
    private  final DashboardRepository dashboardRepository;

    // 의존성 주입 (DI)
    public SalesController(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "sale-add";
    }

    @PostMapping("/add") // DateTimeFormat : 데이터형태의 한 종류 (타임리프에서 받아온 시간데이터를 자바에 맞는 시간데이터로 변경)
    public String addSale(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saleDate,
                          @RequestParam int customerId,
                          @RequestParam BigDecimal amount) {
        dashboardRepository.saveSale(saleDate, customerId, amount);

        return "redirect:/";
    }





}
