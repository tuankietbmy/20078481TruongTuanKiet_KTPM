package dev.yonk.order.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private String shippingAddress;
}
