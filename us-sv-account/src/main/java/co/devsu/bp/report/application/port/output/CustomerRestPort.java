package co.devsu.bp.report.application.port.output;

import co.devsu.bp.report.infrastructure.adapter.rest.dto.CustomerResponseDTO;
import co.devsu.bp.util.exception.EntityWrapperException;

public interface CustomerRestPort {
    CustomerResponseDTO getCustomerByDocumentNumber(String documentNumber) throws EntityWrapperException;
}
