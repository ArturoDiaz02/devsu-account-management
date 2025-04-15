package co.devsu.bp.report.infrastructure.adapter.controller;

import co.devsu.bp.report.application.port.input.ReportServicePort;
import co.devsu.bp.report.infrastructure.adapter.controller.api.ReportApi;
import co.devsu.bp.report.infrastructure.adapter.controller.dto.ReportDTO;
import co.devsu.bp.report.infrastructure.mapper.ReportMapper;
import co.devsu.bp.util.exception.EntityWrapperException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class ReportController implements ReportApi {

    private final ReportMapper reportMapper;
    private final ReportServicePort reportService;

    @Override
    public List<ReportDTO> getReport(String documentNumber, LocalDate limitDate) throws EntityWrapperException {
        return reportMapper.toReportDTOFromReport(
            reportService.generateReport(documentNumber, limitDate.atTime(LocalTime.MAX))
        );
    }
}
