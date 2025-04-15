package co.devsu.bp.account.infrastructure.adapter.controller.dto;

import co.devsu.bp.account.domain.AccountType;
import co.devsu.bp.util.validation.enum_exist.EnumExist;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreatedAccountDTO(
    @NotEmpty
    String documentNumber,
    @EnumExist(enumClass = AccountType.class)
    String type,
    @Min(value = 0)
    Double openingBalance
) {
}
