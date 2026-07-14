package com.rino.pethealthapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long>{

}
