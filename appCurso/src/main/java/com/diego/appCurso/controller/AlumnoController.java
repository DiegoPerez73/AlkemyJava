package com.diego.appCurso.controller;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Role;
import com.diego.appCurso.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> getAll(){

        return alumnoService.getAll();
    }

//---Get por ID ---
    @GetMapping ("/{id}")
    public ResponseEntity<Alumno> getId(@PathVariable Long id){ return alumnoService.getById(id);}

//---ADD alumno ---
    @PostMapping
    public ResponseEntity<Alumno> addAlumno(@Valid @RequestBody Alumno alumno){
        return alumnoService.addAlumno(alumno);
    }

//---Modificar alumno por ID---
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody Alumno alumno) {
            return alumnoService.update(id, alumno);
    }

//---DELETE por Id---
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        return alumnoService.deleteById(id);
    }


}
