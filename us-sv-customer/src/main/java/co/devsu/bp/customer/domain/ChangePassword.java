package co.devsu.bp.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
