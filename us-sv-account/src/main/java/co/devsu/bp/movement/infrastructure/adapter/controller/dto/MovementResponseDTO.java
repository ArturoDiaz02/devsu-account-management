package co.devsu.bp.movement.infrastructure.adapter.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovementResponseDTO(
    Integer accountNumber,
    String type,
    Double amount,
    String createdAt
) {
}
