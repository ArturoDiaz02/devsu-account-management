package co.devsu.bp.movement.application.port.output;

import co.devsu.bp.account.domain.Account;
import co.devsu.bp.movement.domain.Movement;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementPersistencePort {
    List<Movement> findAllMovementsByCreatedAtLessThanEqual(LocalDateTime createdAt);
    List<Movement> findAllMovementsByAccountNumber(Integer accountNumber);
    Movement saveMovement(Movement movement, Account account);
}
