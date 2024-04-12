package dev.yonk.order.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
    private OrderDto orderDto;
    private UserDto userDto;
}
