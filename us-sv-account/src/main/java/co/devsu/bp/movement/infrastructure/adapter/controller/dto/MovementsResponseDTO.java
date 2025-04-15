package co.devsu.bp.movement.infrastructure.adapter.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovementsResponseDTO(
    Integer accountNumber,
    List<MovementListResponseDTO> movements
) {
}
