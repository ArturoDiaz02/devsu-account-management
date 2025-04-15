package co.devsu.bp.report.application;

import co.devsu.bp.account.application.port.output.AccountPersistencePort;
import co.devsu.bp.account.domain.Account;
import co.devsu.bp.movement.domain.MovementType;
import co.devsu.bp.report.application.port.input.ReportServicePort;
import co.devsu.bp.report.application.port.output.CustomerRestPort;
import co.devsu.bp.report.domain.Report;
import co.devsu.bp.report.infrastructure.adapter.rest.dto.CustomerResponseDTO;
import co.devsu.bp.util.exception.EntityWrapperException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class ReportService implements ReportServicePort {

    private final CustomerRestPort customerRestClient;
    private final AccountPersistencePort accountPersistence;

    @Override
    public List<Report> generateReport(String documentNumber, LocalDateTime limitDate) throws EntityWrapperException {
        log.info("Generating report for documentNumber: {} with limitDate: {}", documentNumber, limitDate);
        CustomerResponseDTO customer = customerRestClient.getCustomerByDocumentNumber(documentNumber);
        List<Account> accounts = accountPersistence.findByDocumentNumberAndMovementLimitDate(documentNumber, limitDate);

        log.info("Found {} accounts for documentNumber: {}", accounts.size(), documentNumber);
        return accounts.stream()
            .map(account -> buildReport(account, customer.name(), limitDate))
            .toList();
    }

    private Report buildReport(Account account, String clientName, LocalDateTime limitDate) {
        log.info("Building report for accountNumber: {} with limitDate: {}", account.getAccountNumber(), limitDate);
        double movementAmount = account.getMovements().stream()
            .mapToDouble(m -> m.getType() == MovementType.DEPOSIT ? m.getAmount() : -m.getAmount())
            .sum();

        return Report.builder()
            .date(limitDate)
            .clientName(clientName)
            .accountNumber(account.getAccountNumber())
            .accountType(account.getType())
            .openingBalance(account.getOpeningBalance())
            .accountStatus(account.getStatus())
            .movementAmount(movementAmount)
            .balance(account.getBalance())
            .build();
    }
}
