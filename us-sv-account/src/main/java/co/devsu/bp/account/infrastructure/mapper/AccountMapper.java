package co.devsu.bp.account.infrastructure.mapper;

import co.devsu.bp.account.domain.Account;
import co.devsu.bp.account.infrastructure.adapter.consumer.event.CreatedAccountEvent;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.AccountResponseDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.CreatedAccountDTO;
import co.devsu.bp.account.infrastructure.adapter.controller.dto.UpdatedAccountDTO;
import co.devsu.bp.account.infrastructure.repository.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toDomainFromCreationEvent(CreatedAccountEvent createdAccountEvent);
    Account toDomainFromEntity(AccountEntity accountEntity);
    AccountEntity toEntityFromDomain(Account account);

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "balance", source = "openingBalance")
    @Mapping(target = "accountNumber", qualifiedByName = "generateAccountNumber")
    AccountEntity toEntityFromCreationDomain(Account account);

    AccountResponseDTO toResponseDTOFromDomain(Account account);
    List<AccountResponseDTO> toResponseDTOFromDomain(List<Account> accounts);
    Account toDomainFromCreatedDTO(CreatedAccountDTO accountResponseDTO);
    Account toEntityFromUpdatedDTO(UpdatedAccountDTO accountResponseDTO);

    @Named("generateAccountNumber")
    static Integer generateAccountNumber(Integer accountNumber) {
        return 100000 + (int) (Math.random() * 900000);
    }
}
