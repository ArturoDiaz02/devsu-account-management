package co.devsu.bp.customer.infrastructure.adapter.controller.dto.request;

import co.devsu.bp.customer.domain.AccountType;
import co.devsu.bp.user.domain.Gender;
import co.devsu.bp.util.validation.enum_exist.EnumExist;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CreatedCustomerWithAccountDTO(
    @NotEmpty(message = "Name cannot be empty")
    String name,
    @EnumExist(enumClass = Gender.class)
    String gender,
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    Integer age,
    @NotEmpty(message = "Document number cannot be empty")
    String documentNumber,
    @NotEmpty(message = "Address cannot be empty")
    String address,
    @NotEmpty(message = "Phone cannot be empty")
    String phone,
    @NotEmpty(message = "Email cannot be empty")
    String password,
    @Min(value = 0, message = "Opening balance must be greater than or equal to 0")
    Double openingBalance,
    @EnumExist(enumClass = AccountType.class, message = "Invalid account type")
    String accountType
) {
}
