package arsenypoga.basepermissions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProtectedController {

  @GetMapping("/")
  ResponseEntity<String> unguardedRoute() {
    return ResponseEntity.ok().body("Unguarded");
  }

  @GetMapping("/protected")
  ResponseEntity<String> protectedRoute() {
    return ResponseEntity.ok().body("protected");
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<String> adminRoute() {
    return ResponseEntity.ok().body("admin");
  }

}
