package com.diego.appCurso.services;


import com.diego.appCurso.dto.JwtAuthenticationResponse;
import com.diego.appCurso.dto.SignInRequest;
import com.diego.appCurso.dto.SignUpRequest;
import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.repositories.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AlumnoRepository alumnoRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {

        var alumno = Alumno.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .edad(request.getEdad())
                .build();

        alumnoRepository.save(alumno);

        var jwt = jwtService.generateToken(alumno);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDni(), request.getPassword())
        );

        var alumno = alumnoRepository.findByDni(request.getDni()).orElseThrow(()->new IllegalArgumentException("Invalid dni or password"));

        var jwt = jwtService.generateToken(alumno);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
