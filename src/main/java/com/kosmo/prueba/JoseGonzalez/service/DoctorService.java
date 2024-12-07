package com.kosmo.prueba.JoseGonzalez.service;

import com.kosmo.prueba.JoseGonzalez.entity.Consultorio;
import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import com.kosmo.prueba.JoseGonzalez.reposistory.ConsultorioRepository;
import com.kosmo.prueba.JoseGonzalez.reposistory.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    public List<Doctor> obtenerTodo() {
        return doctorRepository.findAll();
    }

}
