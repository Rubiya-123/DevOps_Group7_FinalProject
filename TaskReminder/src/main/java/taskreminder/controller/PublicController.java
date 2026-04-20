package taskreminder.controller;

import taskreminder.dto.ApiResponse;

import taskreminder.dto.UserDTO;

import taskreminder.entity.User;
import taskreminder.service.UserDetailsServiceImpl;
import taskreminder.service.UserService;
import taskreminder.jwt.JwtUtil;
import java.util.Arrays;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import taskreminder.dto.UserLoginDTO;
import taskreminder.dto.UserResponseDTO;
import taskreminder.dto.UserDTO;
import taskreminder.dto.ApiResponse;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

   
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        String s = userService.saveNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDTO>> login(@RequestBody UserLoginDTO dto) {

        User dbUser = userService.findByUsername(dto.getUsername());

        if (dbUser == null ||
            !passwordEncoder.matches(dto.getPassword(), dbUser.getPassword())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, null, "Incorrect username or password"));
        }

        String jwt = jwtUtil.generateToken(dto.getUsername());

        UserResponseDTO response = new UserResponseDTO(
                jwt,
                dbUser.getUser_id(),
                dbUser.getUsername(),
                dbUser.getEmail()
        );

        return ResponseEntity.ok(new ApiResponse<>(true, response, "Login successful"));
    }
    
    
   
    @PostMapping("/login/testing")//Fixing code
    public void login2(@RequestBody UserLoginDTO dto) {
    }
    
    @PostMapping("/signup/testing")//API error
    public void signup2(@RequestBody UserDTO userDTO) {}

    
}

