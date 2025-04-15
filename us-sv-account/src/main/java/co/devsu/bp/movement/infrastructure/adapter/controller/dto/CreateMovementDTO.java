package co.devsu.bp.movement.infrastructure.adapter.controller.dto;

import co.devsu.bp.movement.domain.MovementType;
import co.devsu.bp.util.validation.enum_exist.EnumExist;
import jakarta.validation.constraints.Min;

public record CreateMovementDTO(
    @EnumExist(enumClass = MovementType.class)
    String type,
    @Min(value = 0, message = "El monto debe ser mayor a 0")
    Double amount
) {
}
