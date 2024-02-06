package Team23.FamilyDoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import Team23.FamilyDoctor.service.UserService;
import Team23.FamilyDoctor.config.LoginForm;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthRestController {

    private final UserService userService;

    @Autowired
    public AuthRestController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:4173")
    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
        System.out.println("Test log from authenticateUser");
        // Simulate authentication using your UserService
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        // Create an Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Set the Authentication object in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT if needed (you can still use your UserService for this)
        String jwt = userService.generateJwtToken(authentication);

        // Get user details from the authenticated token
        userDetails = (UserDetails) authentication.getPrincipal();

        // Prepare response data
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", jwt);
        response.put("username", userDetails.getUsername());
        System.out.println(jwt);

        return ResponseEntity.ok(response);
    }
}
