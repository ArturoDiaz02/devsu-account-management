package co.devsu.bp.util.validation.enum_exist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumExistValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface EnumExist {
    String message() default "The field must be exist in the enum";

    Class<? extends Enum<?>> enumClass();

    boolean ignoreNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
