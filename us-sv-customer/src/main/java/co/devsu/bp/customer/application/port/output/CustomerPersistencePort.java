package co.devsu.bp.customer.application.port.output;

import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.util.exception.NotFoundException;

import java.util.Optional;

public interface CustomerPersistencePort {
    Customer save(Customer customer);
    Customer update(String documentNumber, Customer customer) throws NotFoundException;
    void delete(String documentNumber);
    Optional<Customer> findByDocumentNumber(String documentNumber);
}
