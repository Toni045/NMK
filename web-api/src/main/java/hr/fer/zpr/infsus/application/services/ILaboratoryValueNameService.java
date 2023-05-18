package hr.fer.zpr.infsus.application.services;

import hr.fer.zpr.infsus.domain.dto.LaboratoryValueNameDropdownDTO;

import java.util.List;

public interface ILaboratoryValueNameService {
    List<LaboratoryValueNameDropdownDTO> getLaboratoryValueNameDropdown();
}
