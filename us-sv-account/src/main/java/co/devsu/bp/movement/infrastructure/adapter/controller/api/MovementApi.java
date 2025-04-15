package co.devsu.bp.movement.infrastructure.adapter.controller.api;

import co.devsu.bp.movement.infrastructure.adapter.controller.dto.CreateMovementDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementResponseDTO;
import co.devsu.bp.movement.infrastructure.adapter.controller.dto.MovementsResponseDTO;
import co.devsu.bp.util.exception.NotBalanceException;
import co.devsu.bp.util.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/movements")
public interface MovementApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account-number/{accountNumber}/document-number/{documentNumber}")
    MovementResponseDTO createMovement(
        @PathVariable Integer accountNumber,
        @PathVariable String documentNumber,
        @RequestBody @Valid CreateMovementDTO createMovementDTO
    ) throws NotFoundException, NotBalanceException;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account-number/{accountNumber}")
    MovementsResponseDTO getMovements(
        @PathVariable Integer accountNumber
    );
}
