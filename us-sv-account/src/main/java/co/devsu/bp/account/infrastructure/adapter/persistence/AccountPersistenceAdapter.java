package co.devsu.bp.account.infrastructure.adapter.persistence;

import co.devsu.bp.account.application.port.output.AccountPersistencePort;
import co.devsu.bp.account.domain.Account;
import co.devsu.bp.account.infrastructure.mapper.AccountMapper;
import co.devsu.bp.account.infrastructure.repository.AccountRepository;
import co.devsu.bp.account.infrastructure.repository.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        log.info("Account with documentNumber {} and accountNumber {} created", account.getDocumentNumber(), account.getAccountNumber());
        return accountMapper.toDomainFromEntity(
            accountRepository.save(
                accountMapper.toEntityFromCreationDomain(account)
            )
        );
    }

    @Override
    public Account update(String documentNumber, Integer accountNumber, Account account) {
        log.info("Account with documentNumber {} and accountNumber {} updated", documentNumber, accountNumber);
        AccountEntity currentAccount = Optional.ofNullable(accountRepository.findByAccountNumberAndDocumentNumber(accountNumber, documentNumber))
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return accountMapper.toDomainFromEntity(
            accountRepository.save(
                currentAccount.copyFrom(accountMapper.toEntityFromDomain(account))
            )
        );
    }

    @Override
    public void delete(String documentNumber, Integer accountNumber) {
        log.info("Account with documentNumber {} and accountNumber {} deleted", documentNumber, accountNumber);
        AccountEntity currentAccount = Optional.ofNullable(accountRepository.findByAccountNumberAndDocumentNumber(accountNumber, documentNumber))
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        accountRepository.delete(currentAccount);
    }

    @Override
    public List<Account> findByCustomerId(String documentNumber) {
        log.info("Account with documentNumber {} found", documentNumber);
        return accountRepository.findAllByDocumentNumber(documentNumber)
            .stream()
            .map(accountMapper::toDomainFromEntity)
            .toList();
    }

    @Override
    public List<Account> findByDocumentNumberAndMovementLimitDate(String documentNumber, LocalDateTime limitDate) {
        log.info("Account with documentNumber {} and limitDate {} found", documentNumber, limitDate);
        return accountRepository.findAccountsAndMovementsUpToDateNative(documentNumber, limitDate)
            .stream()
            .map(accountMapper::toDomainFromEntity)
            .toList();
    }

    @Override
    public Optional<Account> findAccountByAccountNumberAndCustomerId(String documentNumber, Integer accountNumber) {
        log.info("Account with accountNumber {} found", accountNumber);
        return Optional.ofNullable(accountRepository.findByAccountNumberAndDocumentNumber(accountNumber, documentNumber))
            .map(accountMapper::toDomainFromEntity);
    }
}
