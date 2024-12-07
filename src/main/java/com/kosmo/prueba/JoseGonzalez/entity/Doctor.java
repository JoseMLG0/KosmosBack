package com.kosmo.prueba.JoseGonzalez.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "DOCTOR")
public class Doctor extends Persona {
    @Column(name = "ESPECIALIDAD")
    private String especialidad;

}