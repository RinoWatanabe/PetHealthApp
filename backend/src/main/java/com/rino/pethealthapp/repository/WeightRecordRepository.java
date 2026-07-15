package com.rino.pethealthapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.WeightRecordEntity;

public interface WeightRecordRepository extends JpaRepository<WeightRecordEntity, Long> {

    List<WeightRecordEntity> findByPet_Id(Long petId);
}
