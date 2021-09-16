package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.exception.BadRequestException;
import ma.gov.prefagadir.application.backend.payload.request.LoginRequest;
import ma.gov.prefagadir.application.backend.payload.response.JwtResponse;
import ma.gov.prefagadir.application.backend.repository.UserRepository;
import ma.gov.prefagadir.application.backend.services.UserDetailsImpl;
import ma.gov.prefagadir.application.backend.utils.GeneralUtils;
import ma.gov.prefagadir.application.backend.utils.JwtUtils;
import ma.gov.prefagadir.application.backend.utils.TotpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TotpUtils totpUtils;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails  = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails, false);
        return ResponseEntity.ok(new JwtResponse(jwt,"Bearer", userDetails.getUsername(), Arrays.asList("PRE_VERIFICATION"),false));
    }

    @PostMapping("/verify")
    @PreAuthorize("hasRole('PRE_VERIFICATION_USER')")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody String  code, @AuthenticationPrincipal UserDetailsImpl user){
        if(!totpUtils.verifyCode(code, user.getSecret())){
            throw new BadRequestException("Code is incorrect");
        }
        String jwt = jwtUtils.generateJwtToken(user,true);
        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", user.getUsername(), GeneralUtils.rolesAsListOfSting(user),true));
    }

}
