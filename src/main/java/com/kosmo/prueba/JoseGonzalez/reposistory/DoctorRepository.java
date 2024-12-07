package com.kosmo.prueba.JoseGonzalez.reposistory;

import com.kosmo.prueba.JoseGonzalez.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
