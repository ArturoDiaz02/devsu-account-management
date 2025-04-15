package co.devsu.bp.movement.infrastructure.adapter.controller;

import co.devsu.bp.movement.application.port.input.MovementServicePort;
import co.devsu.bp.movement.infrastructure.adapter.controller.api.MovementApi;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.CreateMovementDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementListResponseDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementResponseDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementsResponseDTO;
import co.devsu.bp.movement.infrastructure.mapper.MovementMapper;
import co.devsu.bp.util.exception.NotBalanceException;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class MovementController implements MovementApi {

    private final MovementMapper movementMapper;
    private final MovementServicePort movementService;

    @Override
    public MovementResponseDTO createMovement(Integer accountNumber, String documentNumber, CreateMovementDTO createMovementDTO) throws NotFoundException, NotBalanceException {
        return movementMapper.toResponseDTOFromDomain(
            movementService.createMovement(
                movementMapper.toDomainFromCreatedRequest(createMovementDTO),
                accountNumber,
                documentNumber
            )
        );
    }

    @Override
    public MovementsResponseDTO getMovements(Integer accountNumber) {
        List<MovementListResponseDTO> movements = movementMapper.toListResponseDTOFromDomain(
            movementService.findAllMovementsByAccountNumber(accountNumber)
        );
        return MovementsResponseDTO.builder()
            .accountNumber(accountNumber)
            .movements(movements)
            .build();
    }
}
