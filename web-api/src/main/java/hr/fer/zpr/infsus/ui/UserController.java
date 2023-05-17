package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("User")
@CrossOrigin
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<UserDropdownDTO>> getUsersAsDropdown() {
        return ResponseEntity.ok(userService.getUsersAsDropdown());
    }
}
