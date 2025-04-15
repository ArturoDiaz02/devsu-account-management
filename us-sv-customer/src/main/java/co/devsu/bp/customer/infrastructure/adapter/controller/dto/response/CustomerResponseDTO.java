package co.devsu.bp.customer.infrastructure.adapter.controller.dto.response;

public record CustomerResponseDTO(
    String name,
    String gender,
    int age,
    String documentNumber,
    String address,
    String phone,
    String status
) {
}
