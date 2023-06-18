package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Curso;
import com.diego.appCurso.repositories.AlumnoRepository;
import com.diego.appCurso.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    public AlumnoRepository alumnoRepository;


    //---Mostrar los cursos---
    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    //---Mostrar curso por id---
    public ResponseEntity<Curso> getById(Long id){

        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //---Mostrar alumnos por curso---
    public ResponseEntity<List<Alumno>> getAlumnosByCursoId(Long id){

        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()){                                 //Si el curso por id existe
            Curso cursoId = curso.get();                        //Traeme ese curso
            return ResponseEntity.ok(cursoId.getAlumnos());         //Devolveme un ResponseEntiti de los alumnos del curso (Uso el getter) getAlumnos()
        }
        return ResponseEntity.notFound().build();       //Si no existe el curso, arma el Response del error.
    }

    //---Añadir curso---
    public ResponseEntity<Curso> addCurso(Curso curso){

        Curso nuevoCurso = cursoRepository.save(curso);

       return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    //---Asignar alumno al curso---
    public ResponseEntity<Curso> asignarAlumno(Long idCurso, Long idAlumno){
        Optional<Curso> optionalCurso = cursoRepository.findById(idCurso);
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(idAlumno);

        if(optionalCurso.isPresent() && optionalAlumno.isPresent()){
            Curso curso = optionalCurso.get();
            Alumno alumno = optionalAlumno.get();

            curso.addAlumno(alumno);
            alumno.setCurso(curso);

            Curso cursoGuardado = cursoRepository.save(curso);
            return ResponseEntity.ok(cursoGuardado);
        }
        return ResponseEntity.notFound().build();

    }

    //---Desasignar alumno del curso x---
    public ResponseEntity<Curso> desasignarAlumno(Long idCurso, Long idAlumno){
        Optional<Curso> optionalCurso = cursoRepository.findById(idCurso);
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(idAlumno);

        if(optionalCurso.isPresent() && optionalAlumno.isPresent()){
            Curso curso = optionalCurso.get();
            Alumno alumno = optionalAlumno.get();

            curso.deleteAlumno(alumno);
            alumno.setCurso(null);

            Curso cursoGuardado = cursoRepository.save(curso);
            return ResponseEntity.ok(cursoGuardado);
        }
        return ResponseEntity.notFound().build();
    }

    //---Modificar curso---
    public ResponseEntity<Curso> updateCurso(Long id, Curso curso){

    if(!cursoRepository.existsById(id)){            //Si el curso no existe (existsById() es un metodo del CRUD, lo traes de JPA repository(extendiendo en el CursoRepository)
        return ResponseEntity.notFound().build();   //Devuelvo el ResponseEntity y buildeo el error.
    }
    Curso cursoModificado = cursoRepository.save(curso);
    return ResponseEntity.ok(cursoModificado);
    }



}
