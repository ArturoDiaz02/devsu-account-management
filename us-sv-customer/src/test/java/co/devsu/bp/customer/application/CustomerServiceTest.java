package co.devsu.bp.customer.application;

import co.devsu.bp.customer.application.port.input.PasswordServicePort;
import co.devsu.bp.customer.application.port.output.AccountBrokerPort;
import co.devsu.bp.customer.application.port.output.CustomerPersistencePort;
import co.devsu.bp.customer.domain.ChangePassword;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.customer.domain.CustomerStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private AccountBrokerPort accountBroker;
    @Mock
    private PasswordServicePort passwordService;
    @Mock
    private CustomerPersistencePort customerPersistence;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testChangePassword_Successful() throws Exception {
        String documentNumber = "123456789";
        String oldPassword = "oldPass";
        String newPassword = "newPass";
        String confirmNewPassword = "newPass";
        String encodedOldPassword = "encodedOldPass";
        String encodedNewPassword = "encodedNewPass";

        ChangePassword changePasswordRequest = ChangePassword.builder()
            .oldPassword(oldPassword)
            .newPassword(newPassword)
            .confirmNewPassword(confirmNewPassword)
            .build();

        Customer existingCustomer = Customer.builder()
            .documentNumber(documentNumber)
            .password(encodedOldPassword)
            .status(CustomerStatus.ACTIVE)
            .build();

        when(customerPersistence.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(existingCustomer));
        when(passwordService.matches(oldPassword, encodedOldPassword)).thenReturn(true);
        when(passwordService.encode(newPassword)).thenReturn(encodedNewPassword);

        customerService.changePassword(documentNumber, changePasswordRequest);

        verify(customerPersistence).update(eq(documentNumber), argThat(updated ->
            encodedNewPassword.equals(updated.getPassword())));
        verify(passwordService).encode(newPassword);
    }
}
