package co.devsu.bp.movement.application.port.input;

import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.util.exception.NotBalanceException;
import co.devsu.bp.util.exception.NotFoundException;

import java.util.List;

public interface MovementServicePort {
    Movement createMovement(Movement movement, Integer accountNumber, String documentNumber) throws NotBalanceException, NotFoundException;
    List<Movement> findAllMovementsByAccountNumber(Integer accountNumber);
}
