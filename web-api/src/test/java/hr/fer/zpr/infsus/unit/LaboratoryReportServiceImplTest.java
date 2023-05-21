package hr.fer.zpr.infsus.unit;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.application.services.impl.LaboratoryReportServiceImpl;
import hr.fer.zpr.infsus.domain.JPAEntities.LaboratoryReport;
import hr.fer.zpr.infsus.domain.JPAEntities.User;
import hr.fer.zpr.infsus.domain.dto.LaboratoryReportDTO;
import hr.fer.zpr.infsus.domain.mappers.ILaboratoryReportMapper;
import hr.fer.zpr.infsus.domain.request.LaboratoryReportRequest;
import hr.fer.zpr.infsus.infrastructure.LaboratoryReportRepository;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LaboratoryReportServiceImplTest {

    @Mock
    private LaboratoryReportRepository laboratoryReportRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IUserService userService;

    @Mock
    private ILaboratoryReportMapper laboratoryReportMapper;

    private LaboratoryReportServiceImpl laboratoryReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        laboratoryReportService = new LaboratoryReportServiceImpl(laboratoryReportMapper, laboratoryReportRepository, userRepository, userService);
    }

    @Test
    void getAllReports_ShouldReturnLaboratoryReportDTOList() {
        // Mocking laboratoryReportRepository
        List<LaboratoryReport> laboratoryReportList = new ArrayList<>();
        LaboratoryReport laboratoryReport1 = new LaboratoryReport();
        LaboratoryReport laboratoryReport2 = new LaboratoryReport();
        laboratoryReportList.add(laboratoryReport1);
        laboratoryReportList.add(laboratoryReport2);
        when(laboratoryReportRepository.findAll()).thenReturn(laboratoryReportList);

        // Mocking laboratoryReportMapper
        List<LaboratoryReportDTO> expectedDTOList = new ArrayList<>();
        LaboratoryReportDTO laboratoryReportDTO1 = new LaboratoryReportDTO();
        LaboratoryReportDTO laboratoryReportDTO2 = new LaboratoryReportDTO();
        expectedDTOList.add(laboratoryReportDTO1);
        expectedDTOList.add(laboratoryReportDTO2);
        when(laboratoryReportMapper.laboratoryReportsToLaboratoryReportDTOs(laboratoryReportList)).thenReturn(expectedDTOList);

        // Call the getAllReports method
        List<LaboratoryReportDTO> result = laboratoryReportService.getAllReports();

        // Verify the laboratoryReportRepository and laboratoryReportMapper interactions
        verify(laboratoryReportRepository).findAll();
        verify(laboratoryReportMapper).laboratoryReportsToLaboratoryReportDTOs(laboratoryReportList);

        // Assert the expected result
        assertEquals(expectedDTOList, result);
    }

    @Test
    void updateLaboratoryReport_WhenReportExists_ShouldReturnUpdatedReport() {
        // Mocking laboratoryReportRepository
        LaboratoryReport laboratoryReport = new LaboratoryReport();
        when(laboratoryReportRepository.findById(anyInt())).thenReturn(Optional.of(laboratoryReport));
        when(laboratoryReportRepository.save(any(LaboratoryReport.class))).thenReturn(laboratoryReport);

        // Mocking userRepository
        User user = new User();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        // Mocking laboratoryReportMapper
        LaboratoryReportDTO expectedDTO = new LaboratoryReportDTO();
        when(laboratoryReportMapper.laboratoryReportToLaboratoryReportDTO(any(LaboratoryReport.class))).thenReturn(expectedDTO);

        // Create the updateRequest
        LaboratoryReportRequest updateRequest = new LaboratoryReportRequest();
        updateRequest.setId(1);
        updateRequest.setDate(LocalDate.of(2023, 5, 21));
        updateRequest.setDescription("Updated description");
        updateRequest.setUserId(1);

        // Call the updateLaboratoryReport method
        LaboratoryReportDTO result = laboratoryReportService.updateLaboratoryReport(updateRequest);

        // Verify the laboratoryReportRepository and userRepository interactions
        verify(laboratoryReportRepository).findById(1);
        verify(userRepository).findById(1);
        verify(laboratoryReportRepository).save(laboratoryReport);

        // Assert the expected result
        assertEquals(expectedDTO, result);
    }

    @Test
    void updateLaboratoryReport_WhenReportDoesNotExist_ShouldReturnNull() {
        // Mocking laboratoryReportRepository
        when(laboratoryReportRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Create the updateRequest
        LaboratoryReportRequest updateRequest = new LaboratoryReportRequest();
        updateRequest.setId(1);
        updateRequest.setDate(LocalDate.of(2023, 5, 21));
        updateRequest.setDescription("Updated description");
        updateRequest.setUserId(1);

        // Call the updateLaboratoryReport method
        LaboratoryReportDTO result = laboratoryReportService.updateLaboratoryReport(updateRequest);

        // Verify the laboratoryReportRepository interaction
        verify(laboratoryReportRepository).findById(1);

        // Assert the expected result
        assertNull(result);
    }

    @Test
    void updateLaboratoryReport_WhenUserDoesNotExist_ShouldReturnNull() {
        // Mocking laboratoryReportRepository
        LaboratoryReport laboratoryReport = new LaboratoryReport();
        when(laboratoryReportRepository.findById(anyInt())).thenReturn(Optional.of(laboratoryReport));

        // Mocking userRepository
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Create the updateRequest
        LaboratoryReportRequest updateRequest = new LaboratoryReportRequest();
        updateRequest.setId(1);
        updateRequest.setDate(LocalDate.of(2023, 5, 21));
        updateRequest.setDescription("Updated description");
        updateRequest.setUserId(1);

        // Call the updateLaboratoryReport method
        LaboratoryReportDTO result = laboratoryReportService.updateLaboratoryReport(updateRequest);

        // Verify the laboratoryReportRepository and userRepository interactions
        verify(laboratoryReportRepository).findById(1);
        verify(userRepository).findById(1);

        // Assert the expected result
        assertNull(result);
    }

}
