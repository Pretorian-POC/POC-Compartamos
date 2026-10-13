package com.example.payment.demo;

import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/demo")
public class MerchantHealthController {

    @GetMapping("/gateway-check")
    public String gatewayCheck(@RequestParam String gateway) throws Exception {
        String[] command = {"/bin/sh", "-c", "nslookup " + gateway};
        Process proc = Runtime.getRuntime().exec(command);
        return new String(proc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}