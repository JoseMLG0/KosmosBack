package com.kosmo.prueba.JoseGonzalez.service;

import com.kosmo.prueba.JoseGonzalez.dto.CitaNuevaRequest;
import com.kosmo.prueba.JoseGonzalez.entity.Cita;
import com.kosmo.prueba.JoseGonzalez.entity.Consultorio;
import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import com.kosmo.prueba.JoseGonzalez.entity.Paciente;
import com.kosmo.prueba.JoseGonzalez.reposistory.CitaRepository;
import com.kosmo.prueba.JoseGonzalez.reposistory.ConsultorioRepository;
import com.kosmo.prueba.JoseGonzalez.reposistory.DoctorRepository;
import com.kosmo.prueba.JoseGonzalez.reposistory.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ConsultorioRepository consultorioRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public Cita agendarCita(CitaNuevaRequest nuevaCita) throws Exception {
        // Ejecutar Proceso
        return registrarCita(nuevaCita);
    }

    public List<Cita> obtenerTodo() {
        return citaRepository.findAll();
    }

    public void borrarCita(Long id) {
        citaRepository.deleteById(id);
    }

    private Cita registrarCita(CitaNuevaRequest cita) throws Exception {
        LocalDateTime dataTime = toLocalDateTime(cita.getFecha(), cita.getHora());
        // Validar por consultorio
        List<Cita> citasConsultorio = citaRepository.findByConsultorioIdAndHorario(
                cita.getConsultorioId(),
                dataTime
        );
        if (!citasConsultorio.isEmpty())throw new Exception("Consultorio sin horario disponible");

        // Validar por doctor
        List<Cita> citasDoctor = citaRepository.findByDoctorIdAndHorarioBetween(
                cita.getDoctorId(),
                dataTime.toLocalDate().atStartOfDay(),
                dataTime.toLocalDate().atTime(23, 59)
        );
        if (citasDoctor.size() >= 8) throw new Exception("Doctor con mas de 8 citas registradas");

        // Validar por paciente
        LocalDateTime twoHoursBefore = dataTime.minusHours(2);
        LocalDateTime twoHoursAfter = dataTime.plusHours(2);

        Paciente paciente = pacienteRepository.findAllByNombreAndApellidoPaternoAndApellidoMaterno(cita.getNombre(), cita.getApellidoPaterno(), cita.getApellidoMaterno());

        if (paciente == null) {
            Paciente newPaciente = new Paciente();
            newPaciente.setNombre(cita.getNombre());
            newPaciente.setApellidoPaterno(cita.getApellidoPaterno());
            newPaciente.setApellidoMaterno(cita.getApellidoMaterno());
            paciente = pacienteRepository.saveAndFlush(newPaciente);
        }

        List<Cita> citasPaciente = citaRepository.findByPacienteIdAndHorarioBetween(
                paciente.getId(),
                twoHoursBefore,
                twoHoursAfter
        );

        if (!citasPaciente.isEmpty()) throw new Exception("Paciente con cita realizada en un lapso de 2 horas");


        // Proceso de registro
        Optional<Consultorio> consultorio = consultorioRepository.findById(cita.getConsultorioId());
        Optional<Doctor> doctor = doctorRepository.findById(cita.getDoctorId());

        if(consultorio.isEmpty()) throw new Exception("Consultorio no encontrado");
        if(doctor.isEmpty()) throw new Exception("Doctor no encontrado");

        Cita nuevaCita = new Cita();
        nuevaCita.setConsultorio(consultorio.get());
        nuevaCita.setDoctor(doctor.get());
        nuevaCita.setPaciente(paciente);
        nuevaCita.setHorario(dataTime);
        return citaRepository.save(nuevaCita);
    }


    public LocalDateTime toLocalDateTime(String fechaS, String horaS) {
        fechaS = fechaS.replace("Z", "");
        LocalDateTime fecha = LocalDateTime.parse(fechaS);
        LocalTime hora = LocalTime.parse(horaS);
        return fecha.with(hora);
    }

    public boolean isWithinOneHour(LocalDateTime fechaHora, LocalDateTime fechaHoraToCompare) {
        // Calcular la diferencia en horas entre las dos fechas
        Duration duration = Duration.between(fechaHora, fechaHoraToCompare);
        long hoursDifference = Math.abs(duration.toHours());

        // Si la diferencia es menor o igual a 1 hora, retorna true
        return hoursDifference <= 1;
    }
}
