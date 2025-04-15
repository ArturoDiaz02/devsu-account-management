package co.devsu.bp.report.infrastructure.adapter.rest;

import co.devsu.bp.report.application.port.output.CustomerRestPort;
import co.devsu.bp.report.infrastructure.adapter.rest.dto.CustomerResponseDTO;
import co.devsu.bp.report.infrastructure.proxy.CustomerProxy;
import co.devsu.bp.util.common.rest.RestClientValidator;
import co.devsu.bp.util.exception.EntityWrapperException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class CustomerRestClientAdapter extends RestClientValidator implements CustomerRestPort {

    private final CustomerProxy customerProxy;

    @Override
    public CustomerResponseDTO getCustomerByDocumentNumber(String documentNumber) throws EntityWrapperException {
        log.info("Fetching customer by document number: {}", documentNumber);
        return this.executeCall(
            customerProxy.getCustomerByDocumentNumber(documentNumber)
        );
    }
}
