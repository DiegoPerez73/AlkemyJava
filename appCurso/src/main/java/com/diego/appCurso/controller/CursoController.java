package com.diego.appCurso.controller;


import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Curso;
import com.diego.appCurso.services.AlumnoService;
import com.diego.appCurso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;

    }

    //---Get todos los cursos---
    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        return cursoService.getAll();
    }

    //---Get por ID---
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id){
        return cursoService.getById(id);
    }

    //---Get alumnos en el curso pasado por id---
    @GetMapping("/{id}/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnosPorCurso(@PathVariable Long id){
        return cursoService.getAlumnosByCursoId(id);
    }

    //---Post de curso---
    @PostMapping
    public ResponseEntity<Curso> addCurso(@RequestBody Curso curso){
        return cursoService.addCurso(curso);
    }

    //Asignar alumno a un curso
    @PostMapping("/{idCurso}/alumnos/{idAlumno}")
    public ResponseEntity<Curso> asignarCursoAAlumno(@PathVariable Long idCurso, @PathVariable Long idAlumno){
        return cursoService.asignarAlumno(idCurso,idAlumno);
    }

    //---Desasignar alumno de un curso---
    @DeleteMapping("/{idCurso}/alumnos/{idAlumno}")
        public ResponseEntity<Curso> desasignarAlumnoDeCurso(@PathVariable Long idCurso, @PathVariable Long idAlumno){
            return cursoService.desasignarAlumno(idCurso,idAlumno);
        }

    //--Update (PUT) curso---
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso){
        return cursoService.updateCurso(id, curso);
    }

}
