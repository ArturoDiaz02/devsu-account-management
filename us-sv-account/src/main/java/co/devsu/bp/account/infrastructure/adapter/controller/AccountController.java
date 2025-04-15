package co.devsu.bp.account.infrastructure.adapter.controller;

import co.devsu.bp.account.application.port.input.AccountServicePort;
import co.devsu.bp.account.infrastructure.adapter.controller.api.AccountApi;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.AccountResponseDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.CreatedAccountDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.UpdatedAccountDTO;
import co.devsu.bp.account.infrastructure.mapper.AccountMapper;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class AccountController implements AccountApi {

    private final AccountMapper accountMapper;
    private final AccountServicePort accountService;

    @Override
    public List<AccountResponseDTO> getAccountByCustomerId(String documentNumber) {
        log.info("Getting accounts for customer {}", documentNumber);
        return accountMapper.toResponseDTOFromDomain(
            accountService.findAccountsByDocumentNumber(documentNumber)
        );
    }

    @Override
    public AccountResponseDTO getAccountByCustomerIdAndAccountNumber(String documentNumber, Integer accountNumber) throws NotFoundException {
        log.info("Getting account with number {} for customer {}", accountNumber, documentNumber);
        return accountMapper.toResponseDTOFromDomain(
            accountService.findAccountByAccountNumberAndCustomerId(documentNumber, accountNumber)
        );
    }

    @Override
    public AccountResponseDTO createAccount(CreatedAccountDTO accountResponseDTO) {
        log.info("Creating account for customer {}", accountResponseDTO.documentNumber());
        return accountMapper.toResponseDTOFromDomain(
            accountService.createAccount(
                accountMapper.toDomainFromCreatedDTO(accountResponseDTO)
            )
        );
    }

    @Override
    public AccountResponseDTO updateAccount(String documentNumber, Integer accountNumber, UpdatedAccountDTO accountResponseDTO) {
        log.info("Updating account with number {} for customer {}", accountNumber, documentNumber);
        return accountMapper.toResponseDTOFromDomain(
            accountService.updateAccount(
                documentNumber,
                accountNumber,
                accountMapper.toEntityFromUpdatedDTO(accountResponseDTO)
            )
        );
    }

    @Override
    public void deleteAccount(String documentNumber, Integer accountNumber) {
        log.info("Deleting account with number {} for customer {}", accountNumber, documentNumber);
        accountService.deleteAccount(documentNumber, accountNumber);
    }
}
