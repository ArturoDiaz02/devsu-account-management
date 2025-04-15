package co.devsu.bp.account.infrastructure.adapter.controller.dto;

import co.devsu.bp.account.domain.AccountStatus;
import co.devsu.bp.account.domain.AccountType;
import co.devsu.bp.util.validation.enum_exist.EnumExist;
import lombok.Builder;

@Builder
public record UpdatedAccountDTO(
    @EnumExist(enumClass = AccountType.class, ignoreNull = true)
    String type,
    @EnumExist(enumClass = AccountStatus.class, ignoreNull = true)
    String status
) {
}
