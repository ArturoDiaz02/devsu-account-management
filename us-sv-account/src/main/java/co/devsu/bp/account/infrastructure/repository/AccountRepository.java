package co.devsu.bp.account.infrastructure.repository;

import co.devsu.bp.account.infrastructure.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    List<AccountEntity> findAllByDocumentNumber(String documentNumber);

    @Query(value = """
            SELECT DISTINCT a.* FROM account a
            LEFT JOIN movement m ON a.account_id = m.account_id
            WHERE a.document_number = :documentNumber
              AND (m.created_at <= :endDate)
        """, nativeQuery = true)
    List<AccountEntity> findAccountsAndMovementsUpToDateNative(
        @Param("documentNumber") String documentNumber,
        @Param("endDate") LocalDateTime endDate
    );

    AccountEntity findByAccountNumberAndDocumentNumber(Integer accountNumber, String documentNumber);
}
