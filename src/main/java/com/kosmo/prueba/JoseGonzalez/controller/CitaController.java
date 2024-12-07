package com.kosmo.prueba.JoseGonzalez.controller;

import com.kosmo.prueba.JoseGonzalez.dto.CitaNuevaRequest;
import com.kosmo.prueba.JoseGonzalez.entity.Cita;
import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import com.kosmo.prueba.JoseGonzalez.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cita")
@CrossOrigin(origins = "*")
public class CitaController  {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<?> listarCitas() {
        List<Cita> data = citaService.obtenerTodo();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json"));
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarCita(@RequestBody CitaNuevaRequest citaNuevaRequest) throws Exception {
        Cita data = citaService.agendarCita(citaNuevaRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json"));
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{cita}")
    public ResponseEntity<?> borrarCita(@PathVariable ("cita") Long cita) throws Exception {
        citaService.borrarCita(cita);
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }
}