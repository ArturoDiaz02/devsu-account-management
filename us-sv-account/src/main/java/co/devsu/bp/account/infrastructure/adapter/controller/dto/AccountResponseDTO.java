package co.devsu.bp.account.infrastructure.adapter.controller.dto;

import lombok.Builder;

@Builder
public record AccountResponseDTO(
     String documentNumber,
     Integer accountNumber,
     String type,
     String status,
     Double balance,
     Double openingBalance
) {
}
