package dev.yonk.order.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
}
