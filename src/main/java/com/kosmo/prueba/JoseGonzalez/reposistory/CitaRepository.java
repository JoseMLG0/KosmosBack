package com.kosmo.prueba.JoseGonzalez.reposistory;

import com.kosmo.prueba.JoseGonzalez.entity.Cita;
import com.kosmo.prueba.JoseGonzalez.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorIdAndHorarioBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Cita> findByConsultorioIdAndHorario(Long consultorioId, LocalDateTime horario);
    List<Cita> findByPacienteIdAndHorarioBetween(Long pacienteId, LocalDateTime start, LocalDateTime end);

}
