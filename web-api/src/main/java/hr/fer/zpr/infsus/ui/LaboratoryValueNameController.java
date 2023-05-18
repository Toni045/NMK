package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.ILaboratoryValueNameService;
import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<LaboratoryValueNameDropdownDTO> updateLaboratoryValueName(@PathVariable Integer id,@RequestBody String laboratoryValueName){
        return ResponseEntity.ok(laboratoryValueNameService.updateLaboratoryValueName(id,laboratoryValueName));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<Void> deleteLaboratoryValueName(@PathVariable Integer id){
        laboratoryValueNameService.deleteLaboratoryValueName(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','DOCTOR')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<LaboratoryValueNameDropdownDTO> createLaboratoryValueName(@RequestBody String name){

        return ResponseEntity.ok(laboratoryValueNameService.createLaboratoryValueName(name));
    }
}
