package com.dcep.payment.controller;

import com.dcep.payment.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Payment Processing, Fraud Detection, Transaction Status, Refunds
 *
 * POC / MVP controller backed by an in-memory store.
 * Swap the Map for a JPA repository + real database once the
 * contract with the frontend / other services is confirmed.
 */
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final Map<Long, Payment> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("payment-service is UP");
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAll() {
        return ResponseEntity.ok(List.copyOf(store.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        Payment item = store.get(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payload) {
        Long id = idSequence.getAndIncrement();
        payload.setId(id);
        store.put(id, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment payload) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        payload.setId(id);
        store.put(id, payload);
        return ResponseEntity.ok(payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        store.remove(id);
        return ResponseEntity.noContent().build();
    }

}
