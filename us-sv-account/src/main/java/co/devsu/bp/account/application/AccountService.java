package co.devsu.bp.account.application;

import co.devsu.bp.account.application.port.input.AccountServicePort;
import co.devsu.bp.account.application.port.output.AccountPersistencePort;
import co.devsu.bp.account.domain.Account;
import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.movement.domain.MovementType;
import co.devsu.bp.util.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountService implements AccountServicePort {

    private final AccountPersistencePort accountPersistence;

    @Override
    public Account createAccount(Account account) {
        log.info("Creating account: {}", account);
        return accountPersistence.save(account);
    }

    @Override
    @Transactional
    public Account updateAccount(String documentNumber, Integer accountNumber, Account account) {
        log.info("Updating account: {} for documentNumber: {}", account, documentNumber);
        return accountPersistence.update(documentNumber, accountNumber, account);
    }

    @Override
    @Transactional
    public void deleteAccount(String documentNumber, Integer accountNumber) {
        log.info("Deleting account with accountNumber: {} for customerId: {}", accountNumber, documentNumber);
        accountPersistence.delete(documentNumber, accountNumber);
    }

    @Override
    public List<Account> findAccountsByDocumentNumber(String documentNumber) {
        log.info("Finding accounts for customerId: {}", documentNumber);
        return accountPersistence.findByCustomerId(documentNumber);
    }

    @Override
    public Account findAccountByAccountNumberAndCustomerId(String documentNumber, Integer accountNumber) throws NotFoundException {
        log.info("Finding account with accountNumber: {}", accountNumber);
        return accountPersistence.findAccountByAccountNumberAndCustomerId(documentNumber, accountNumber)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Async
    @Override
    @Transactional
    public void updateBalance(Account account, Movement movement) {
        log.info("Updating account balance");
        if (movement.getType().equals(MovementType.WITHDRAWAL)) {
            log.info("Withdrawing amount: {}", movement.getAmount());
            account.setBalance(account.getBalance() - movement.getAmount());
        } else if (movement.getType().equals(MovementType.DEPOSIT)) {
            log.info("Depositing amount: {}", movement.getAmount());
            account.setBalance(account.getBalance() + movement.getAmount());
        }

        log.info("Saving account with new balance: {}", account.getBalance());
        accountPersistence.update(account.getDocumentNumber(), account.getAccountNumber(), account);
    }

}
