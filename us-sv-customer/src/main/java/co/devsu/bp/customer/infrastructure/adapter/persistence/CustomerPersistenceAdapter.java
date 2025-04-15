package co.devsu.bp.customer.infrastructure.adapter.persistence;

import co.devsu.bp.customer.application.port.output.CustomerPersistencePort;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.customer.infrastructure.mapper.CustomerMapper;
import co.devsu.bp.customer.infrastructure.repository.CustomerRepository;
import co.devsu.bp.customer.infrastructure.repository.entity.CustomerEntity;
import co.devsu.bp.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
class CustomerPersistenceAdapter implements CustomerPersistencePort {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        log.info("Saving customer with DNI: {}", customer.getDocumentNumber());
        return customerMapper.toDomainFromEntity(
            customerRepository.save(
                customerMapper.toEntityFromDomain(customer)
            )
        );
    }

    @Override
    public Customer update(String documentNumber, Customer customer) throws NotFoundException {
        log.info("Updating customer with DNI: {}", documentNumber);
        CustomerEntity customerEntity = Optional.ofNullable(customerRepository.findByDocumentNumber(documentNumber))
            .map(entity -> entity.copyFrom(customerMapper.toEntityFromDomain(customer)))
            .orElseThrow(() -> new NotFoundException("User not found"));

        return customerMapper.toDomainFromEntity(
            customerRepository.save(
                customerEntity
            )
        );
    }

    @Override
    public void delete(String documentNumber) {
        log.info("Deleting customer with DNI: {}", documentNumber);
        customerRepository.deleteByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        log.info("Finding customer by document number: {}", documentNumber);
        return Optional.ofNullable(customerRepository.findByDocumentNumber(documentNumber))
            .map(customerMapper::toDomainFromEntity);
    }
}
