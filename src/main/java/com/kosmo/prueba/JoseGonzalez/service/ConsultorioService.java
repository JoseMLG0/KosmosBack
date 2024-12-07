package com.kosmo.prueba.JoseGonzalez.service;

import com.kosmo.prueba.JoseGonzalez.entity.Cita;
import com.kosmo.prueba.JoseGonzalez.entity.Consultorio;
import com.kosmo.prueba.JoseGonzalez.reposistory.CitaRepository;
import com.kosmo.prueba.JoseGonzalez.reposistory.ConsultorioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Transactional
    public List<Consultorio> obtenerTodo() {
        return consultorioRepository.findAll();
    }

}
