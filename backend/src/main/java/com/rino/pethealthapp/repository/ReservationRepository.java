package com.rino.pethealthapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.ReservationEntity;

public interface ReservationRepository
                extends JpaRepository<ReservationEntity, Long> {

        List<ReservationEntity> findByPet_Id(Long petId);

        // 指定したペットの予約から、今日以前のものを対象にして、予約日の新しい順で先頭の1件を取得する
        Optional<ReservationEntity> 
                findTopByPet_IdAndAppointmentDateLessThanEqualOrderByAppointmentDateDesc(
                        Long petId,
                        LocalDate today);

        // 今日以降の予約を予約日の近い順で取得する
        List<ReservationEntity> 
                findByAppointmentDateGreaterThanEqualOrderByAppointmentDateAsc(
                LocalDate today);

        }
