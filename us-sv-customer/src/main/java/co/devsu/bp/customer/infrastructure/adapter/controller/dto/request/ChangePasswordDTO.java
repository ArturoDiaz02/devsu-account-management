package co.devsu.bp.customer.infrastructure.adapter.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ChangePasswordDTO(
    @NotEmpty
    String oldPassword,
    @NotEmpty
    String newPassword,
    @NotEmpty
    String confirmNewPassword
) {

}
