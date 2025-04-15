package co.devsu.bp.report.infrastructure.adapter.controller.api;

import co.devsu.bp.report.infrastructure.adapter.controller.dto.ReportDTO;
import co.devsu.bp.util.exception.EntityWrapperException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/v1/report")
public interface ReportApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ReportDTO> getReport(
        @RequestParam(value = "documentNumber") String documentNumber,
        @RequestParam(value = "date") LocalDate limitDate
    ) throws EntityWrapperException;
}
