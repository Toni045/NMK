package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryValuesService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("LaboratoryValues")
@CrossOrigin
public class LaboratoryValuesController {
    private final ILaboratoryValuesService laboratoryValuesService;

    public LaboratoryValuesController(ILaboratoryValuesService laboratoryValuesService) {
        this.laboratoryValuesService = laboratoryValuesService;
    }

    @GetMapping("/{laboratoryReportId}")
    public ResponseEntity<List<LaboratoryValueDTO>> getAllLaboratoryValuesForLaboratoryReport(@PathVariable Integer laboratoryReportId) {
        return ResponseEntity.ok(laboratoryValuesService.laboratoryValuesForLaboratoryReport(laboratoryReportId));
    }
}
