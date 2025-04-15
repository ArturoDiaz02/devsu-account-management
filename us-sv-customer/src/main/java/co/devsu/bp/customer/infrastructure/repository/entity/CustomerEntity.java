package co.devsu.bp.customer.infrastructure.repository.entity;

import co.devsu.bp.customer.domain.CustomerStatus;
import co.devsu.bp.user.infrastructure.repository.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerEntity extends UserEntity {

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CustomerStatus status;

    @CreationTimestamp
    @Column(name = "customer_created_at", updatable = false)
    private LocalDateTime customerCreatedAt;

    @UpdateTimestamp
    @Column(name = "customer_updated_at", insertable = false)
    private LocalDateTime customerUpdatedAt;

    @Override
    public CustomerEntity copyFrom(UserEntity other) {
        super.copyFrom(other);
        if (other instanceof CustomerEntity customerEntity) {
            Optional.ofNullable(customerEntity.getPassword()).ifPresent(this::setPassword);
            Optional.ofNullable(customerEntity.getStatus()).ifPresent(this::setStatus);
        }
        return this;
    }

}
