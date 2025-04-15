package co.devsu.bp.movement.infrastructure.repository;

import co.devsu.bp.movement.infrastructure.repository.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, String> {
    List<MovementEntity> findAllByAccount_AccountNumber(Integer accountNumber);
    List<MovementEntity> findAllByCreatedAtLessThanEqual(LocalDateTime createdAt);
}
