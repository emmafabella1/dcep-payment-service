package com.dcep.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private String orderId;
    private Double amount;
    private String status;
    private String method;
}
