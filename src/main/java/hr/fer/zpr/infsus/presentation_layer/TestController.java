package hr.fer.zpr.infsus.presentation_layer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@CrossOrigin
public class TestController {
    @GetMapping
    public ResponseEntity<String> getTestString() {
        return ResponseEntity.ok("This is a test!");
    }

    @GetMapping("secured")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getSecuredTestString() {
        return ResponseEntity.ok("This is a secured test!");
    }
}
