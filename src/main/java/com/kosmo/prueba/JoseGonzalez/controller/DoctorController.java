package com.kosmo.prueba.JoseGonzalez.controller;

import com.kosmo.prueba.JoseGonzalez.entity.Cita;
import com.kosmo.prueba.JoseGonzalez.entity.Consultorio;
import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import com.kosmo.prueba.JoseGonzalez.service.CitaService;
import com.kosmo.prueba.JoseGonzalez.service.ConsultorioService;
import com.kosmo.prueba.JoseGonzalez.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> obtenerTodo() {
        List<Doctor> data = doctorService.obtenerTodo();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json"));
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}