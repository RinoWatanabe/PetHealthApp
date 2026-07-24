package com.rino.pethealthapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.HospitalEntity;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    Optional<HospitalEntity> findByHospitalName(String hospitalName);
}
