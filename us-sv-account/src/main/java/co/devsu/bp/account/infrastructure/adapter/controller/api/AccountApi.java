package co.devsu.bp.account.infrastructure.adapter.controller.api;

import co.devsu.bp.account.infrastructure.adapter.controller.dto.AccountResponseDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.CreatedAccountDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.UpdatedAccountDTO;
import co.devsu.bp.util.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/accounts")
public interface AccountApi {

    @GetMapping("/document-number/{documentNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<AccountResponseDTO> getAccountByCustomerId(
        @PathVariable String documentNumber
    );

    @GetMapping("/{accountNumber}/document-number/{documentNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    AccountResponseDTO getAccountByCustomerIdAndAccountNumber(
        @PathVariable String documentNumber,
        @PathVariable Integer accountNumber
    ) throws NotFoundException;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponseDTO createAccount(
        @RequestBody @Valid CreatedAccountDTO accountResponseDTO
    );

    @PutMapping("/{accountNumber}/document-number/{documentNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    AccountResponseDTO updateAccount(
        @PathVariable String documentNumber,
        @PathVariable Integer accountNumber,
        @RequestBody @Valid UpdatedAccountDTO accountResponseDTO
    );

    @DeleteMapping("/{accountNumber}/document-number/{documentNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAccount(
        @PathVariable String documentNumber,
        @PathVariable Integer accountNumber
    );
}
