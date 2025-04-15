package co.devsu.bp.customer.application;

import co.devsu.bp.customer.application.port.input.CustomerServicePort;
import co.devsu.bp.customer.application.port.input.PasswordServicePort;
import co.devsu.bp.customer.application.port.output.AccountBrokerPort;
import co.devsu.bp.customer.application.port.output.CustomerPersistencePort;
import co.devsu.bp.customer.domain.ChangePassword;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.customer.domain.CustomerStatus;
import co.devsu.bp.util.exception.AlreadyExistException;
import co.devsu.bp.util.exception.ChangePasswordException;
import co.devsu.bp.util.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class CustomerService implements CustomerServicePort {

    private final AccountBrokerPort accountBroker;
    private final PasswordServicePort passwordService;
    private final CustomerPersistencePort customerPersistence;

    @Override
    public Customer save(Customer customer) throws AlreadyExistException {

        log.info("Validating if customer with document number {} already exists", customer.getDocumentNumber());
        if (customerPersistence.findByDocumentNumber(customer.getDocumentNumber()).isPresent()) {
            log.error("Customer with document number {} already exists", customer.getDocumentNumber());
            throw new AlreadyExistException("Customer already exists");
        }

        customer.setPassword(passwordService.encode(customer.getPassword()));
        customer.setStatus(CustomerStatus.ACTIVE);
        return customerPersistence.save(customer);
    }

    @Override
    public Customer save(Customer customer, Double openingBalance, String accountType) throws JsonProcessingException, AlreadyExistException {
        Customer newCustomer = this.save(customer);
        log.info("Creating account for customer with document number {}", customer.getDocumentNumber());
        accountBroker.createAccount(newCustomer, openingBalance, accountType);
        return newCustomer;
    }

    @Override
    @Transactional
    public Customer update(String documentNumber, Customer customer) throws NotFoundException {
        return customerPersistence.update(documentNumber, customer);
    }

    @Override
    @Transactional
    public void delete(String documentNumber) {
        customerPersistence.delete(documentNumber);
    }

    @Override
    public Customer findByDocumentNumber(String documentNumber) throws NotFoundException {
        return customerPersistence.findByDocumentNumber(documentNumber)
            .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    @Transactional
    public void changePassword(String documentNumber, ChangePassword password) throws ChangePasswordException, NotFoundException {
        if (!password.getNewPassword().equals(password.getConfirmNewPassword())) {
            log.error("New password and confirmation do not match for customer with document number: {}", documentNumber);
            throw new ChangePasswordException("New password and confirmation do not match");
        }

        log.info("Changing password for customer with document number: {}", documentNumber);
        Customer customer = this.findByDocumentNumber(documentNumber);

        log.info("Checking if old password matches for customer with document number: {}", documentNumber);
        if (passwordService.matches(password.getOldPassword(), customer.getPassword())) {
            log.info("Updating password for customer with document number: {}", documentNumber);
            customerPersistence.update(
                documentNumber,
                customer.withPassword(passwordService.encode(password.getNewPassword())
                ));
            return;
        }

        log.error("Old password does not match for customer with document number: {}", documentNumber);
        throw new ChangePasswordException("Old password does not match");
    }
}
