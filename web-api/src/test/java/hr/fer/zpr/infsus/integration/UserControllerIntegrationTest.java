package hr.fer.zpr.infsus.integration;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import hr.fer.zpr.infsus.ui.contoller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @MockBean
    private IUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsersAsDropdown() throws Exception {
        List<UserDropdownDTO> userDropdownDTOList = Arrays.asList(
                new UserDropdownDTO(1, "John@doe"),
                new UserDropdownDTO(2, "Jane@jane")
        );

        given(userService.getUsersAsDropdown()).willReturn(userDropdownDTOList);

        mockMvc.perform(get("/User/dropdown"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("John@doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].email").value("Jane@jane"));

        verify(userService, times(1)).getUsersAsDropdown();
        verifyNoMoreInteractions(userService);
    }

}
