package Team23.FamilyDoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Team23.FamilyDoctor.service.UserService;
import Team23.FamilyDoctor.entity.User;
import Team23.FamilyDoctor.dto.LoginForm;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
        // Authenticate the user using your UserService
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Generate JWT if authentication is successful
        String jwt = userService.generateJwtToken(authentication);

        // Get user details from the authenticated token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Prepare response data
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", jwt);
        response.put("username", userDetails.getUsername());
        // Add more user details as needed

        return ResponseEntity.ok(response);
    }
}
