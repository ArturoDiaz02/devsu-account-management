package co.devsu.bp.account.domain;

import co.devsu.bp.movement.domain.Movement;
import lombok.*;

import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String documentNumber;
    private Integer accountNumber;
    private AccountType type;
    private AccountStatus status;
    private Double balance;
    private Double openingBalance;
    private List<Movement> movements;
}
