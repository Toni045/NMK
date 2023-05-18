package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.services.ILaboratoryReportService;
import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.JPAEntities.EUserType;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryReport;
import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryReportMapper;
import hr.fer.zpr.infsus.domain.request.LaboratoryReportRequest;
import hr.fer.zpr.infsus.infrastructure.LaboratoryReportRepository;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LaboratoryReportServiceImpl implements ILaboratoryReportService {
    private final ILaboratoryReportMapper laboratoryReportMapper;
    private final LaboratoryReportRepository laboratoryReportRepository;
    private final UserRepository userRepository;
    private final IUserService userService;

    public LaboratoryReportServiceImpl(ILaboratoryReportMapper laboratoryReportMapper, LaboratoryReportRepository laboratoryReportRepository, UserRepository userRepository, IUserService userService) {
        this.laboratoryReportRepository = laboratoryReportRepository;
        this.laboratoryReportMapper = laboratoryReportMapper;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<LaboratoryReportDTO> getAllReports() {
        return laboratoryReportMapper.laboratoryReportsToLaboratoryReportDTOs(laboratoryReportRepository.findAll());
    }

    @Override
    public LaboratoryReportDTO updateLaboratoryReport(LaboratoryReportRequest updateRequest) {
        LaboratoryReport newLaboratoryReport = laboratoryReportRepository.findById(updateRequest.getId()).orElseGet(() -> null);
        if (newLaboratoryReport == null) {
            return null;
        }
        if (updateRequest.getDate() != null) {
            newLaboratoryReport.setDate(updateRequest.getDate());
        }
        if (updateRequest.getDescription() != null) {
            newLaboratoryReport.setDescription(updateRequest.getDescription());
        }
        if (updateRequest.getUserId() != null) {
            Optional<User> user = userRepository.findById(updateRequest.getUserId());
            if (user.isEmpty()) {
                return null;
            }
            newLaboratoryReport.setUser(user.get());
        }
        laboratoryReportRepository.save(newLaboratoryReport);
        return laboratoryReportMapper.laboratoryReportToLaboratoryReportDTO(newLaboratoryReport);
    }

    @Override
    public LaboratoryReportDTO createLaboratoryReport(LaboratoryReportRequest updateRequest) {
        if (updateRequest.getId() != null) {
            return null;
        }
        if (updateRequest.getDescription() == null || updateRequest.getDate() == null || updateRequest.getUserId() == null) {
            return null;
        }
        LaboratoryReport newLaboratoryReport = new LaboratoryReport();
        newLaboratoryReport.setDate(updateRequest.getDate());
        newLaboratoryReport.setDescription(updateRequest.getDescription());
        Optional<User> user = userRepository.findById(updateRequest.getUserId());
        if (user.isEmpty()) {
            return null;
        }
        newLaboratoryReport.setUser(user.get());
        laboratoryReportRepository.save(newLaboratoryReport);
        return laboratoryReportMapper.laboratoryReportToLaboratoryReportDTO(newLaboratoryReport);
    }

    @Override
    public void deleteLaboratoryReport(int id) {
        laboratoryReportRepository.deleteById(id);
    }

    @Override
    public List<LaboratoryReportDTO> getAllReportsForUser(int userId) {
        return laboratoryReportMapper.laboratoryReportsToLaboratoryReportDTOs(laboratoryReportRepository.findByUser_Id(userId));
    }

    @Override
    public LaboratoryReportDTO getReportById(Integer id) {
        UserDTO user=userService.getCurrentUser();
        Optional<LaboratoryReport> optionalLaboratoryReport=laboratoryReportRepository.findById(id);
        if(optionalLaboratoryReport.isEmpty()){
            return null;
        }
        if(Objects.equals(user.getUserType(), EUserType.USER.name()) && !Objects.equals(user.getId(), optionalLaboratoryReport.get().getUser().getId())){
            return null;
        }
        return laboratoryReportMapper.laboratoryReportToLaboratoryReportDTO(optionalLaboratoryReport.get());
    }

    @Override
    public List<LaboratoryReportDTO> getMyReports() {
        UserDTO user=userService.getCurrentUser();
        return laboratoryReportMapper.laboratoryReportsToLaboratoryReportDTOs(laboratoryReportRepository.findByUser_Id(user.getId()));
    }
}
