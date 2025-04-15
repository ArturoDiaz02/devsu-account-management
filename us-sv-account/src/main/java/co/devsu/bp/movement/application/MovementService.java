package co.devsu.bp.movement.application;

import co.devsu.bp.account.application.port.input.AccountServicePort;
import co.devsu.bp.account.domain.Account;
import co.devsu.bp.movement.application.port.input.MovementServicePort;
import co.devsu.bp.movement.application.port.output.MovementPersistencePort;
import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.movement.domain.MovementType;
import co.devsu.bp.util.exception.NotBalanceException;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class MovementService implements MovementServicePort {

    private final AccountServicePort accountService;
    private final MovementPersistencePort movementPersistence;

    @Override
    public Movement createMovement(Movement movement, Integer accountNumber, String documentNumber) throws NotBalanceException, NotFoundException {
        log.info("Getting account by account number: {} and document number: {}", accountNumber, documentNumber);
        Account account = accountService.findAccountByAccountNumberAndCustomerId(documentNumber, accountNumber);

        log.info("Validate if account is active and has balance");
        if (movement.getType().equals(MovementType.WITHDRAWAL) && account.getBalance() < movement.getAmount()) {
            log.info("Account is not active or has no balance");
            throw new NotBalanceException("Account has no balance");
        }

        log.info("Saving movement");
        Movement newMovement = movementPersistence.saveMovement(movement, account);
        accountService.updateBalance(account, newMovement);
        return newMovement;
    }

    @Override
    public List<Movement> findAllMovementsByAccountNumber(Integer accountNumber) {
        return movementPersistence.findAllMovementsByAccountNumber(accountNumber);
    }

}
