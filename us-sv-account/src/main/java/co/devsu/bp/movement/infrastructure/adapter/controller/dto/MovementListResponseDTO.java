package co.devsu.bp.movement.infrastructure.adapter.controller.dto;

import lombok.Builder;

@Builder
public record MovementListResponseDTO(
    String type,
    Double amount,
    String createdAt
) {
}
