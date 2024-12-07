package com.kosmo.prueba.JoseGonzalez.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "PACIENTE")
public class Paciente extends Persona {
    @Column(name = "NUMERO_SEGURO")
    private Long numeroSeguro;

}