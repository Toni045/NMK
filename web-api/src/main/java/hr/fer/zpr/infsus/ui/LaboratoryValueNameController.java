package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryValueNameService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("LaboratoryValueName")
@CrossOrigin
public class LaboratoryValueNameController {
    private final ILaboratoryValueNameService laboratoryValueNameService;

    public LaboratoryValueNameController(ILaboratoryValueNameService laboratoryValueNameService) {
        this.laboratoryValueNameService = laboratoryValueNameService;
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<LaboratoryValueNameDropdownDTO>> getLaboratoryValueNameDropdown(){
        return ResponseEntity.ok(laboratoryValueNameService.getLaboratoryValueNameDropdown());
    }
}
