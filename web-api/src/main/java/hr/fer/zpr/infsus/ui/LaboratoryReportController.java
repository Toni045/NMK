package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryReportService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.request.LaboratoryReportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryReportDTO> getReportById(@PathVariable Integer id) {
        LaboratoryReportDTO result=laboratoryReportService.getReportById(id);
        if(result==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<LaboratoryReportDTO> updateReport(@RequestBody LaboratoryReportRequest updateRequest) {
        LaboratoryReportDTO result = laboratoryReportService.updateLaboratoryReport(updateRequest);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<LaboratoryReportDTO> createReport(@RequestBody LaboratoryReportRequest updateRequest) {
        LaboratoryReportDTO result = laboratoryReportService.createLaboratoryReport(updateRequest);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable int id) {
        laboratoryReportService.deleteLaboratoryReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LaboratoryReportDTO>> getAllReportsForUser(@PathVariable int userId) {
        List<LaboratoryReportDTO> result=laboratoryReportService.getAllReportsForUser(userId);
        if(result==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
