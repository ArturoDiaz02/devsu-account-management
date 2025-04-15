package co.devsu.bp.report.application.port.input;

import co.devsu.bp.report.domain.Report;
import co.devsu.bp.util.exception.EntityWrapperException;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportServicePort {
    List<Report> generateReport(String documentNumber, LocalDateTime limitDate) throws EntityWrapperException;
}
