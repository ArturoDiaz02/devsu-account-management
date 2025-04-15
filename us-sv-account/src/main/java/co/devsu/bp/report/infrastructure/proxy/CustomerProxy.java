package co.devsu.bp.report.infrastructure.proxy;

import co.devsu.bp.report.infrastructure.adapter.rest.dto.CustomerResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CustomerProxy {
    @GET("/v1/customers/document-number/{documentNumber}")
    Call<CustomerResponseDTO> getCustomerByDocumentNumber(
        @Path("documentNumber") String documentNumber
    );
}
