package co.devsu.bp.movement.infrastructure.adapter.persistence;

import co.devsu.bp.account.domain.Account;
import co.devsu.bp.account.infrastructure.mapper.AccountMapper;
import co.devsu.bp.movement.application.port.output.MovementPersistencePort;
import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.movement.infrastructure.mapper.MovementMapper;
import co.devsu.bp.movement.infrastructure.repository.MovementRepository;
import co.devsu.bp.movement.infrastructure.repository.entity.MovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class MovementPersistenceAdapter implements MovementPersistencePort {

    private final AccountMapper accountMapper;
    private final MovementMapper movementMapper;
    private final MovementRepository movementRepository;


    @Override
    public List<Movement> findAllMovementsByCreatedAtLessThanEqual(LocalDateTime createdAt) {
        log.info("Finding all movements created before or at: {}", createdAt);
        return movementMapper.toDomainFromEntity(
            movementRepository.findAllByCreatedAtLessThanEqual(createdAt)
        );
    }

    @Override
    public List<Movement> findAllMovementsByAccountNumber(Integer accountNumber) {
        log.info("Finding all movements for account number: {}", accountNumber);
        return movementMapper.toDomainFromEntity(
            movementRepository.findAllByAccount_AccountNumber(accountNumber)
        );
    }

    @Override
    public Movement saveMovement(Movement movement, Account account) {
        log.info("Saving movement: {}", movement);
        MovementEntity movementEntity = movementMapper.toEntityFromDomain(movement);
        return movementMapper.toDomainFromEntity(
            movementRepository.save(
                movementEntity.withAccount(accountMapper.toEntityFromDomain(account))
            )
        );
    }
}
