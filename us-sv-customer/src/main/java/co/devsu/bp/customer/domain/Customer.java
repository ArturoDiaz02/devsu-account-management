package co.devsu.bp.customer.domain;

import co.devsu.bp.user.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@With
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {
    private String password;
    private CustomerStatus status;
}
