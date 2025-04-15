package co.devsu.bp.report.infrastructure.mapper;

import co.devsu.bp.report.domain.Report;
import co.devsu.bp.report.infrastructure.adapter.controller.dto.ReportDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDTO reportToReportDTO(Report report);
    List<ReportDTO> toReportDTOFromReport(List<Report> reportList);
}
