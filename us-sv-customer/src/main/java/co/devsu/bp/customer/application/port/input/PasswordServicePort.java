package co.devsu.bp.customer.application.port.input;

public interface PasswordServicePort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
