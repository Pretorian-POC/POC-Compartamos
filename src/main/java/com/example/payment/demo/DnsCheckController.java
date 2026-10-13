package com.example.payment.demo;

import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/live")
public class DnsCheckController {

    @GetMapping("/dns-check")
    public String dnsCheck(@RequestParam String domain) throws Exception {
        String[] shellCommand = {"/bin/sh", "-c", "dig +short " + domain};
        Process lookup = Runtime.getRuntime().exec(shellCommand);
        return new String(lookup.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
