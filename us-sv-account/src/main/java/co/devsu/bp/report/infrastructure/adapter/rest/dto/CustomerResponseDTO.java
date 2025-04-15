package co.devsu.bp.report.infrastructure.adapter.rest.dto;

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
