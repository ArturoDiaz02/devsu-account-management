package co.devsu.bp.customer.infrastructure.adapter.controller;

import co.devsu.bp.customer.application.port.input.CustomerServicePort;
import co.devsu.bp.customer.infrastructure.adapter.controller.api.CustomerApi;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.ChangePasswordDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerWithAccountDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.UpdatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.response.CustomerResponseDTO;
import co.devsu.bp.customer.infrastructure.mapper.CustomerMapper;
import co.devsu.bp.util.exception.AlreadyExistException;
import co.devsu.bp.util.exception.ChangePasswordException;
import co.devsu.bp.util.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class CustomerController implements CustomerApi {

    private final CustomerMapper customerMapper;
    private final CustomerServicePort customerService;

    @Override
    public CustomerResponseDTO getCustomerByDocumentNumber(String documentNumber) throws NotFoundException {
        log.info("Fetching customer with document number: {}", documentNumber);
        return customerMapper.toResponseDTOFromDomain(
            customerService.findByDocumentNumber(documentNumber)
        );
    }

    @Override
    public CustomerResponseDTO createCustomer(CreatedCustomerDTO customer) throws AlreadyExistException {
        log.info("Creating customer with document number: {}", customer.documentNumber());
        return customerMapper.toResponseDTOFromDomain(
            customerService.save(
                customerMapper.toDomainFromCreatedDTO(customer)
            )
        );
    }

    @Override
    public CustomerResponseDTO createCustomerWithAccount(CreatedCustomerWithAccountDTO customer) throws JsonProcessingException, AlreadyExistException {
        log.info("Creating customer with account with document number: {}", customer.documentNumber());
        return customerMapper.toResponseDTOFromDomain(
            customerService.save(
                customerMapper.toDomainFromCreatedDTO(customer),
                customer.openingBalance(),
                customer.accountType()
            )
        );
    }

    @Override
    public CustomerResponseDTO updateCustomer(String documentNumber, UpdatedCustomerDTO customer) throws NotFoundException {
        log.info("Updating customer with document number: {}", documentNumber);
        return customerMapper.toResponseDTOFromDomain(
            customerService.update(
                documentNumber,
                customerMapper.toDomainFromUpdatedDTO(customer)
            )
        );
    }

    @Override
    public void changePassword(String documentNumber, ChangePasswordDTO customer) throws ChangePasswordException, NotFoundException {
        log.info("Changing password for customer with document number: {}", documentNumber);
        customerService.changePassword(
            documentNumber,
            customerMapper.toDomainFromChangePasswordDTO(customer)
        );
    }

    @Override
    public void deleteCustomer(String documentNumber) {
        log.info("Deleting customer with document number: {}", documentNumber);
        customerService.delete(documentNumber);
    }
}
