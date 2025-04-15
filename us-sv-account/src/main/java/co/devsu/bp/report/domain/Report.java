package co.devsu.bp.report.domain;

import co.devsu.bp.account.domain.AccountStatus;
import co.devsu.bp.account.domain.AccountType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Report {
    private LocalDateTime date;
    private String clientName;
    private Integer accountNumber;
    private AccountType accountType;
    private Double openingBalance;
    private AccountStatus accountStatus;
    private Double movementAmount;
    private Double balance;
}
