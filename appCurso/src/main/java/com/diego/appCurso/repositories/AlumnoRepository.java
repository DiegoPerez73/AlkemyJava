package com.diego.appCurso.repositories;


import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {


  //      List<Alumno> findAllByRoleIn(List<Role> role);

        Optional<Alumno> findByDni(String dni);

}
