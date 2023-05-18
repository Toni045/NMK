package hr.fer.zpr.infsus.ui;

import hr.fer.zpr.infsus.application.services.IUserService;
import hr.fer.zpr.infsus.domain.JPAEntities.EUserType;
import hr.fer.zpr.infsus.domain.dto.UserDTO;
import hr.fer.zpr.infsus.domain.dto.UserDropdownDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}/{typeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @SecurityRequirement(name = "BearerAuthentication")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable Integer userId, @RequestBody Integer newType){
        return ResponseEntity.ok(userService.updateUser(userId,newType));
    }
}
