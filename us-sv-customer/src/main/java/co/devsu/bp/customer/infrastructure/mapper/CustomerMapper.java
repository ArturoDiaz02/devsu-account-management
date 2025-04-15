package co.devsu.bp.customer.infrastructure.mapper;

import co.devsu.bp.customer.domain.ChangePassword;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.ChangePasswordDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerWithAccountDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.UpdatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.response.CustomerResponseDTO;
import co.devsu.bp.customer.infrastructure.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toDomainFromEntity(CustomerEntity customerEntity);
    CustomerEntity toEntityFromDomain(Customer customer);

    CustomerResponseDTO toResponseDTOFromDomain(Customer customer);
    Customer toDomainFromCreatedDTO(CreatedCustomerDTO customerResponseDTO);
    Customer toDomainFromCreatedDTO(CreatedCustomerWithAccountDTO customerResponseDTO);
    Customer toDomainFromUpdatedDTO(UpdatedCustomerDTO customerResponseDTO);
    ChangePassword toDomainFromChangePasswordDTO(ChangePasswordDTO changePasswordDTO);
}
