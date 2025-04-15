package co.devsu.bp.movement.infrastructure.repository.entity;

import co.devsu.bp.account.infrastructure.repository.entity.AccountEntity;
import co.devsu.bp.movement.domain.MovementType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movement")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementEntity {

    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movement_id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JsonIgnoreProperties("movements")
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private MovementType type;

    @Column(name = "amount", nullable = false, updatable = false)
    private Double amount;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
