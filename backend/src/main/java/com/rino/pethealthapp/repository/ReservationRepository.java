package com.rino.pethealthapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByPet_Id(Long petId);

}
