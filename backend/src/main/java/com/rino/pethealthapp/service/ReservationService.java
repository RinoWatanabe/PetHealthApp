package com.rino.pethealthapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.entity.ReservationEntity;
import com.rino.pethealthapp.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 指定したペットIDの予約一覧を取得する.
     *
     * @param petId ペットID
     * @return 予約一覧
     */
    public List<ReservationEntity> findAllByPetId(Long petId) {
        return reservationRepository.findByPet_Id(petId);
    }

    /**
     * 指定したIDの予約情報を取得する.
     * 
     * @param id 予約ID
     * @return 予約情報
     */
    public Optional<ReservationEntity> findById(Long id) {
        return reservationRepository.findById(id);
    }

    /**
     * 予約を登録する.
     * 
     * @param reservationEntity 登録する予約情報
     * @return 登録した予約の情報
     */
    public ReservationEntity create(ReservationEntity reservationEntity) {
        return reservationRepository.save(reservationEntity);
    }

    /**
     * 指定したIDの予約情報を更新する.
     * 
     * @param id                予約ID
     * @param reservationEntity 更新する予約情報
     * @return 更新した予約情報
     */
    public ReservationEntity update(Long id, ReservationEntity reservationEntity) {

        ReservationEntity targetReservation = reservationRepository.findById(id).orElseThrow();

        targetReservation.setPet(reservationEntity.getPet());
        targetReservation.setAppointmentDate(reservationEntity.getAppointmentDate());
        targetReservation.setHospital(reservationEntity.getHospital());
        targetReservation.setVisitReason(reservationEntity.getVisitReason());

        return reservationRepository.save(targetReservation);
    }

    /**
     * 指定したIDの予約を削除する.
     * 
     * @param id 予約ID
     */
    public void delete(Long id) {

        ReservationEntity targetReservation = reservationRepository.findById(id).orElseThrow();

        reservationRepository.delete(targetReservation);
    }
}
