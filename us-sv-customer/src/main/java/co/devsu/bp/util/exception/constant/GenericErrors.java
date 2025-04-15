package co.devsu.bp.util.exception.constant;

import lombok.Getter;

@Getter
public enum GenericErrors {
    GEN_ALL_01("En este momento no estamos disponibles - Espera unos minutos para continuar.");

    private final String message;

    GenericErrors(final String message) {
        this.message = message;
    }
}
