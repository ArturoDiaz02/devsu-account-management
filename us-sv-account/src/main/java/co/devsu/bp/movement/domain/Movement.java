package co.devsu.bp.movement.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Integer accountNumber;
    private MovementType type;
    private Double amount;
    private LocalDateTime createdAt;
}
