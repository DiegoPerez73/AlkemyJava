package com.diego.appCurso.dto;

import com.diego.appCurso.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String nombre;
    private String apellido;
    private Integer edad;
    private String dni;
    private String password;
    private Role role;

}
