package com.diego.appCurso.controller;


import com.diego.appCurso.dto.JwtAuthenticationResponse;
import com.diego.appCurso.dto.SignInRequest;
import com.diego.appCurso.dto.SignUpRequest;
import com.diego.appCurso.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SignInRequest request){
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
