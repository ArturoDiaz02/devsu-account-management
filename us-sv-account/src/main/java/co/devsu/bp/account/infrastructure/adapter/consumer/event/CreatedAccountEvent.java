package co.devsu.bp.account.infrastructure.adapter.consumer.event;

import lombok.Builder;

@Builder
public record CreatedAccountEvent(
    String documentNumber,
    String type,
    Double openingBalance
) {
}
