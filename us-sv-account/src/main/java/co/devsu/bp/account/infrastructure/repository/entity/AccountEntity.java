package co.devsu.bp.account.infrastructure.repository.entity;

import co.devsu.bp.account.domain.AccountStatus;
import co.devsu.bp.account.domain.AccountType;
import co.devsu.bp.movement.infrastructure.repository.entity.MovementEntity;
import co.devsu.bp.util.common.entity.CopyEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountEntity implements CopyEntity<AccountEntity> {

    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", updatable = false, nullable = false)
    private String id;

    @Column(name = "document_number", nullable = false, updatable = false)
    private String documentNumber;

    @Column(name = "account_number", unique = true, nullable = false, updatable = false)
    private Integer accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "opening_balance", nullable = false, updatable = false)
    private Double openingBalance;

    @JsonIgnoreProperties("account")
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementEntity> movements;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;


    @Override
    public AccountEntity copyFrom(AccountEntity item) {
        if (item != null) {
            Optional.ofNullable(item.getType()).ifPresent(this::setType);
            Optional.ofNullable(item.getStatus()).ifPresent(this::setStatus);
            Optional.ofNullable(item.getBalance()).ifPresent(this::setBalance);
        }
        return this;
    }
}
