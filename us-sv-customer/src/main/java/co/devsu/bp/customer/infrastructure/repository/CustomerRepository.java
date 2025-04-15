package co.devsu.bp.customer.infrastructure.repository;

import co.devsu.bp.customer.infrastructure.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByDocumentNumber(String documentNumber);
    void deleteByDocumentNumber(String documentNumber);
}
