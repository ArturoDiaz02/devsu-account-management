package co.devsu.bp.customer.application.port.input;

import co.devsu.bp.customer.domain.ChangePassword;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.util.exception.AlreadyExistException;
import co.devsu.bp.util.exception.ChangePasswordException;
import co.devsu.bp.util.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CustomerServicePort {
    Customer save(Customer customer) throws AlreadyExistException;
    Customer save(Customer customer, Double openingBalance, String accountType) throws JsonProcessingException, AlreadyExistException;
    Customer update(String documentNumber, Customer customer) throws NotFoundException;
    void delete(String documentNumber);
    Customer findByDocumentNumber(String documentNumber) throws NotFoundException;
    void changePassword(String documentNumber, ChangePassword password) throws ChangePasswordException, NotFoundException;
}
