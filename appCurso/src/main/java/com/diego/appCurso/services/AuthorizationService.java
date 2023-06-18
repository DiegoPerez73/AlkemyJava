package com.diego.appCurso.services;


import com.diego.appCurso.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    AlumnoRepository alumnoRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return alumnoRepository.findByDni(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
            }
        };
    }


}
