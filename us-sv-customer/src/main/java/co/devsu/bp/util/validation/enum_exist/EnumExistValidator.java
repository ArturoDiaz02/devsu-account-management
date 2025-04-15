package co.devsu.bp.util.validation.enum_exist;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumExistValidator implements ConstraintValidator<EnumExist, String> {

    private Class<? extends Enum<?>> enumClass;
    private boolean ignoreNull;

    @Override
    public void initialize(EnumExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        enumClass = constraintAnnotation.enumClass();
        ignoreNull = constraintAnnotation.ignoreNull();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (ignoreNull && (value == null || value.isEmpty())) {
            return true;
        } else if (value == null || value.isEmpty()) {
            buildDynamicMessage(context);
            return false;
        }

        boolean match = Arrays.stream(enumClass.getEnumConstants())
            .map(Enum::name)
            .anyMatch(name -> name.equals(value.replace("-", "_").toUpperCase()));

        if (!match) {
            buildDynamicMessage(context);
        }

        return match;
    }

    private void buildDynamicMessage(ConstraintValidatorContext context) {
        String validValues = Arrays.stream(enumClass.getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.joining(", "));

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Valor inválido. Valores permitidos: " + validValues)
            .addConstraintViolation();
    }
}
