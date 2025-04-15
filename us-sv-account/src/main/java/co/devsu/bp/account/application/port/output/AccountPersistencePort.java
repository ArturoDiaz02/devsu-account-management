package co.devsu.bp.account.application.port.output;

import co.devsu.bp.account.domain.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {
    Account save(Account account);
    Account update(String documentNumber, Integer accountNumber, Account account);
    void delete(String documentNumber, Integer accountNumber);
    List<Account> findByCustomerId(String documentNumber);
    List<Account> findByDocumentNumberAndMovementLimitDate(String documentNumber, LocalDateTime limitDate);
    Optional<Account> findAccountByAccountNumberAndCustomerId(String documentNumber, Integer accountNumber);
}
