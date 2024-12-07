package com.kosmo.prueba.JoseGonzalez.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "CITA")
@Getter
@Setter
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "HORARIO")
    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "CONSULTORIO_ID", nullable = false)
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    private Paciente paciente;

}