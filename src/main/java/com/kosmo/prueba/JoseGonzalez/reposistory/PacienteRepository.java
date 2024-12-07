package com.kosmo.prueba.JoseGonzalez.reposistory;

import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import com.kosmo.prueba.JoseGonzalez.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findAllByNombreAndApellidoPaternoAndApellidoMaterno(String nombre, String AP, String AM);
}
