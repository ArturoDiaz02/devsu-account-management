package co.devsu.bp.account.application.port.input;

import co.devsu.bp.account.domain.Account;
import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.util.exception.NotFoundException;

import java.util.List;

public interface AccountServicePort {
    Account createAccount(Account account);
    Account updateAccount(String documentNumber, Integer accountNumber, Account account);
    void deleteAccount(String documentNumber, Integer accountNumber);
    List<Account> findAccountsByDocumentNumber(String documentNumber);
    Account findAccountByAccountNumberAndCustomerId(String documentNumber, Integer accountNumber) throws NotFoundException;
    void updateBalance(Account account, Movement movement);
}
