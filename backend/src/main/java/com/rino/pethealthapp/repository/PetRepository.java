package com.rino.pethealthapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long>{

}
