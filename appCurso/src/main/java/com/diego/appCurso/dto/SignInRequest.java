package com.diego.appCurso.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {        //Para el sign in necesito el usuario y password,(En este caso usuario=dni,password=password)

    private String dni;
    private String password;
}
