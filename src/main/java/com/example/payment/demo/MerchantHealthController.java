package com.example.payment.demo;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/demo")
public class MerchantHealthController {

    private static final Map<String, String> ALLOWED_GATEWAYS = Map.of(
            "stripe", "api.stripe.com",
            "adyen", "checkout.adyen.com");

    @GetMapping("/gateway-check")
    public ResponseEntity<String> gatewayCheck(@RequestParam String gateway) throws Exception {
        String hostname = ALLOWED_GATEWAYS.get(gateway);
        if (hostname == null) {
            return ResponseEntity.badRequest().body("Unknown gateway");
        }
        Process proc = new ProcessBuilder("nslookup", hostname).start();
        return ResponseEntity.ok(new String(proc.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
    }
}