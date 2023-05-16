package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryReportService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("LaboratoryReport")
@CrossOrigin
public class LaboratoryReportController {
    private final ILaboratoryReportService laboratoryReportService;

    public LaboratoryReportController(ILaboratoryReportService laboratoryReportService) {
        this.laboratoryReportService = laboratoryReportService;
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryReportDTO>> getAllReports() {
        return ResponseEntity.ok(laboratoryReportService.getAllReports());
    }
}
