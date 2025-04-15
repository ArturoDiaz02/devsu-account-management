package co.devsu.bp.report.infrastructure.adapter.controller.dto;

import co.devsu.bp.account.domain.AccountStatus;
import co.devsu.bp.account.domain.AccountType;

public record ReportDTO(
    String date,
    String clientName,
    Integer accountNumber,
    AccountType accountType,
    Double openingBalance,
    AccountStatus accountStatus,
    Double movementAmount,
    Double balance
) {
}
