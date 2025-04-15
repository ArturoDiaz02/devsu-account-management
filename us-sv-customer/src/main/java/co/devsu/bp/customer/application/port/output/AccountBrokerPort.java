package co.devsu.bp.customer.application.port.output;

import co.devsu.bp.customer.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AccountBrokerPort {
    void createAccount(Customer customer, Double openingBalance, String accountType) throws JsonProcessingException;
}
