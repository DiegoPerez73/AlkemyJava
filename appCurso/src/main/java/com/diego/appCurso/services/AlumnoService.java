package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Role;
import com.diego.appCurso.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;



@Service
public class AlumnoService{

    @Autowired
    AlumnoRepository alumnoRepository;

    //---Mostrar Informacion---

    public ResponseEntity<List<Alumno>> getAll() {

        return ResponseEntity.ok(alumnoRepository.findAll());
    }

    //---Retornar alumno por ID---

    public ResponseEntity<Alumno> getById(Long id){

        return alumnoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //---Eliminar un alumno---
    public ResponseEntity<Void> deleteById(Long id){
        if(!alumnoRepository.existsById(id)){       //Si no existe el alumno con ese Id
            return ResponseEntity.notFound().build();   //Retorno el not found y lo buildeo.
        }
        alumnoRepository.deleteById(id);            //Sino, lo borro. (es un metodo void)
        return ResponseEntity.noContent().build();  //Devuelvo un Response noContent.
    }

    //---Añadir alumnos---

    public ResponseEntity<Alumno> addAlumno(Alumno alumno) {
        Alumno alumnoCreado = alumnoRepository.save(alumno);        //Guardo al alumno creado
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoCreado);

    }

    //---Update de alumno---
    public ResponseEntity<Alumno> update(Long id, Alumno nuevoAlumno) {

        if(!alumnoRepository.existsById(id)){                           //Si no existe el alumno con ese id -->
            return ResponseEntity.notFound().build();                   //Retorno un Response de notFound y lo buildeo
        }
        nuevoAlumno.setId(id);                                          //Le seteo el id a mano para que lo mantenga
        Alumno alumnoGuardado = alumnoRepository.save(nuevoAlumno);     //Lo guardo
        return ResponseEntity.ok(alumnoGuardado);                       //Devuelvo el Response ok del alumno guardado
    }

}
