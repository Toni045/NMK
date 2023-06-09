package hr.fer.zpr.infsus.ui.contoller;

import hr.fer.zpr.infsus.application.services.ILaboratoryReportService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.request.LaboratoryReportRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<List<LaboratoryReportDTO>> getAllReports() {
        return ResponseEntity.ok(laboratoryReportService.getAllReports());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<LaboratoryReportDTO> getReportById(@PathVariable Integer id) {
        LaboratoryReportDTO result = laboratoryReportService.getReportById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<LaboratoryReportDTO> updateReport(@Valid @RequestBody LaboratoryReportRequest updateRequest) {
        LaboratoryReportDTO result = laboratoryReportService.updateLaboratoryReport(updateRequest);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<LaboratoryReportDTO> createReport(@Valid @RequestBody LaboratoryReportRequest updateRequest) {
        LaboratoryReportDTO result = laboratoryReportService.createLaboratoryReport(updateRequest);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<Void> deleteReport(@PathVariable int id) {
        laboratoryReportService.deleteLaboratoryReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<List<LaboratoryReportDTO>> getAllReportsForUser(@PathVariable int userId) {
        List<LaboratoryReportDTO> result = laboratoryReportService.getAllReportsForUser(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/myReports")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<List<LaboratoryReportDTO>> getMyReports() {
        List<LaboratoryReportDTO> result = laboratoryReportService.getMyReports();
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
