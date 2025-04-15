package co.devsu.bp.customer.infrastructure.adapter.producer.event;

import lombok.Builder;

@Builder
public record CreatedAccountEvent(
    String documentNumber,
    String type,
    Double openingBalance
) {
}
