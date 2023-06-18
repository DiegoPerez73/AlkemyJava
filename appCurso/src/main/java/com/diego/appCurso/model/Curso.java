package com.diego.appCurso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nombreCurso;

    @JsonIgnore
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Alumno> alumnos;

    public void addAlumno(Alumno alumno) {
        if (Objects.isNull(alumnos)) {
            alumnos = new ArrayList<>();
        }
        alumnos.add(alumno);
    }
    public void deleteAlumno(Alumno alumno) {
        if(!Objects.isNull(alumnos)) {
            alumnos.remove(alumno);
        }
    }


}
