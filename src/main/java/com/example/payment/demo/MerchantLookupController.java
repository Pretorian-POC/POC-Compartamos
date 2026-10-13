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
public class MerchantLookupController {

    private final JdbcTemplate jdbc;

    public MerchantLookupController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/by-merchant")
    public List<Map<String, Object>> byMerchant(@RequestParam String merchant) {
        String sql = "SELECT id, merchant_id, amount, currency, status FROM payments "
                + "WHERE merchant_id = ?";
        return jdbc.queryForList(sql, merchant);
    }
}