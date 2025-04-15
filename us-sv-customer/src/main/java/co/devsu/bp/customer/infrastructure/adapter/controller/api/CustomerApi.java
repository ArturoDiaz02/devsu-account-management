package co.devsu.bp.customer.infrastructure.adapter.controller.api;

import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.ChangePasswordDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.CreatedCustomerWithAccountDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.request.UpdatedCustomerDTO;
import co.devsu.bp.customer.infrastructure.adapter.controller.dto.response.CustomerResponseDTO;
import co.devsu.bp.util.exception.AlreadyExistException;
import co.devsu.bp.util.exception.ChangePasswordException;
import co.devsu.bp.util.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/customers")
public interface CustomerApi {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/document-number/{documentNumber}")
    CustomerResponseDTO getCustomerByDocumentNumber(
        @PathVariable String documentNumber
    ) throws NotFoundException;

    @PostMapping("/with-out-account")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDTO createCustomer(
        @RequestBody @Valid CreatedCustomerDTO customer
    ) throws JsonProcessingException, AlreadyExistException;

    @PostMapping("/with-account")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDTO createCustomerWithAccount(
        @RequestBody @Valid CreatedCustomerWithAccountDTO customer
    ) throws JsonProcessingException, AlreadyExistException;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/document-number/{documentNumber}")
    CustomerResponseDTO updateCustomer(
        @PathVariable String documentNumber,
        @RequestBody @Valid UpdatedCustomerDTO customer
    ) throws NotFoundException;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/document-number/{documentNumber}")
    void changePassword(
        @PathVariable String documentNumber,
        @RequestBody @Valid ChangePasswordDTO customer
    ) throws ChangePasswordException, NotFoundException;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/document-number/{documentNumber}")
    void deleteCustomer(
        @PathVariable String documentNumber
    );

}
