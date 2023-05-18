package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryValuesService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueDTO;
import hr.fer.zpr.infsus.domain.request.LaboratoryValueRequest;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoryValue(@PathVariable Integer id){
        laboratoryValuesService.deleteLaboratoryValue(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryValueDTO> updateLaboratoryValue(@PathVariable Integer id, @RequestBody LaboratoryValueRequest request){
        LaboratoryValueDTO result=laboratoryValuesService.updateLaboratoryValue(id,request);
        if(result==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{laboratoryReportId}")
    public ResponseEntity<LaboratoryValueDTO> createNewLaboratoryValue(@PathVariable Integer laboratoryReportId,@RequestBody LaboratoryValueRequest request){
        LaboratoryValueDTO result=laboratoryValuesService.createNewLaboratoryValue(laboratoryReportId,request);
        if(result==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{laboratoryReportId}/{laboratoryValueNameId}")
    public ResponseEntity<List<LaboratoryValueDTO>> getAllLaboratoryValuesForLaboratoryReportWithName(@PathVariable Integer laboratoryReportId,@PathVariable Integer laboratoryValueNameId) {
        return ResponseEntity.ok(laboratoryValuesService.getAllLaboratoryValuesForLaboratoryReportWithName(laboratoryReportId,laboratoryValueNameId));
    }
}
