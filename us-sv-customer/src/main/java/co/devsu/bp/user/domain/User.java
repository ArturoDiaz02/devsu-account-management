package co.devsu.bp.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.SuperBuilder;

@Data
@With
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private Gender gender;
    private int age;
    private String documentNumber;
    private String address;
    private String phone;
}
