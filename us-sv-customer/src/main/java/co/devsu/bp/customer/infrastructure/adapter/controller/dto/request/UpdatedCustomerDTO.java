package co.devsu.bp.customer.infrastructure.adapter.controller.dto.request;

import co.devsu.bp.customer.domain.CustomerStatus;
import co.devsu.bp.util.validation.enum_exist.EnumExist;

public record UpdatedCustomerDTO(
    String name,
    String gender,
    Integer age,
    String address,
    String phone,
    @EnumExist(enumClass = CustomerStatus.class, ignoreNull = true)
    String status
) {
}
