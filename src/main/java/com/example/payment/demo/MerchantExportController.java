package com.example.payment.demo;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/demo")
public class MerchantExportController {

    private final JdbcTemplate jdbc;

    public MerchantExportController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/export")
    public List<Map<String, Object>> exportByCurrency(@RequestParam String currency) {
        String query = "SELECT id, merchant_id, amount FROM payments WHERE currency = '" + currency + "'";
        return jdbc.queryForList(query);
    }
}