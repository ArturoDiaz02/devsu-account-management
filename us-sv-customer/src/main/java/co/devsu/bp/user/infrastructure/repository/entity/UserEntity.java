package co.devsu.bp.user.infrastructure.repository.entity;

import co.devsu.bp.user.domain.Gender;
import co.devsu.bp.util.common.entity.CopyEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements CopyEntity<UserEntity> {

    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private Integer age;

    @Column(name = "document_number", nullable = false, unique = true, updatable = false)
    private String documentNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @CreationTimestamp
    @Column(name = "user_created_at", updatable = false)
    private LocalDateTime userCreatedAt;

    @UpdateTimestamp
    @Column(name = "user_updated_at", insertable = false)
    private LocalDateTime userUpdatedAt;

    @Override
    public UserEntity copyFrom(UserEntity item) {
        if (item != null) {
            Optional.ofNullable(item.getName()).ifPresent(this::setName);
            Optional.ofNullable(item.getGender()).ifPresent(this::setGender);
            Optional.ofNullable(item.getAge()).ifPresent(this::setAge);
            Optional.ofNullable(item.getAddress()).ifPresent(this::setAddress);
            Optional.ofNullable(item.getPhone()).ifPresent(this::setPhone);
        }
        return this;
    }
}
