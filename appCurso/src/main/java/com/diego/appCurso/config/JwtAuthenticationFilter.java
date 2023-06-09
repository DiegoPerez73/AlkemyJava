package com.diego.appCurso.config;


import com.diego.appCurso.services.AuthorizationService;
import com.diego.appCurso.services.JwtService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final AuthorizationService authorizationService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
                                    throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userDni;
        if(StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userDni = jwtService.extractUserName(jwt);
        if(StringUtils.isNotEmpty(userDni) && SecurityContextHolder.getContext().getAuthentication()== null){
            UserDetails userDetails = authorizationService.userDetailsService().loadUserByUsername(userDni);

            if(jwtService.isTokenValid(jwt, userDetails)){
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request,response);
    }


}
