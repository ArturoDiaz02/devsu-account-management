package co.devsu.bp.movement.infrastructure.mapper;

import co.devsu.bp.movement.domain.Movement;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.CreateMovementDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementListResponseDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementResponseDTO;
import co.devsu.bp.movement.infrastructure.repository.entity.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    @Mapping(target = "accountNumber", source = "entity.account.accountNumber")
    Movement toDomainFromEntity(MovementEntity entity);
    Movement toDomainFromCreatedRequest(CreateMovementDTO entity);
    List<Movement> toDomainFromEntity(List<MovementEntity> entities);
    MovementEntity toEntityFromDomain(Movement domain);


    MovementResponseDTO toResponseDTOFromDomain(Movement domain);
    List<MovementListResponseDTO> toListResponseDTOFromDomain(List<Movement> domain);
}
