package com.rino.pethealthapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.dto.request.ReservationRequest;
import com.rino.pethealthapp.entity.HospitalEntity;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.entity.ReservationEntity;
import com.rino.pethealthapp.repository.HospitalRepository;
import com.rino.pethealthapp.repository.PetRepository;
import com.rino.pethealthapp.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PetRepository petRepository;
    private final HospitalRepository hospitalRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            PetRepository petRepository,
            HospitalRepository hospitalRepository) {
        this.reservationRepository = reservationRepository;
        this.petRepository = petRepository;
        this.hospitalRepository = hospitalRepository;
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
     * @param reservationRequest 登録する予約情報
     * @return 登録した予約の情報
     */
    public ReservationEntity create(ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity = new ReservationEntity();

        // （1）ペットIDからPetEntityを取得
        Long petId = reservationRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow();

        reservationEntity.setPet(pet);

        // （2）予約日
        reservationEntity.setAppointmentDate(reservationRequest.getAppointmentDate());

        // （3）病院名からHospitalEntityを取得
        HospitalEntity hospital = hospitalRepository
                .findByHospitalName(reservationRequest.getHospitalName())
                .orElseGet(() -> {
                    HospitalEntity newHospital = new HospitalEntity();
                    newHospital.setHospitalName(reservationRequest.getHospitalName());
                    return hospitalRepository.save(newHospital);
                });

        reservationEntity.setHospital(hospital);

        // （4）通院の目的
        reservationEntity.setVisitReason(reservationRequest.getVisitReason());

        return reservationRepository.save(reservationEntity);
    }

    /**
     * 指定したIDの予約情報を更新する.
     * 
     * @param id                 予約ID
     * @param reservationRequest 更新する予約情報
     * @return 更新した予約情報
     */
    public ReservationEntity update(Long id, ReservationRequest reservationRequest) {

        ReservationEntity targetReservation = reservationRepository.findById(id).orElseThrow();

        // （1）ペットIDからPetEntityを取得
        Long petId = reservationRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow();

        targetReservation.setPet(pet);

        // （2）予約日
        targetReservation.setAppointmentDate(reservationRequest.getAppointmentDate());

        // （3）病院名からHospitalEntityを取得
        HospitalEntity hospital = hospitalRepository
                .findByHospitalName(reservationRequest.getHospitalName())
                .orElseGet(() -> {
                    HospitalEntity newHospital = new HospitalEntity();
                    newHospital.setHospitalName(reservationRequest.getHospitalName());
                    return hospitalRepository.save(newHospital);
                });

        targetReservation.setHospital(hospital);

        // （4）通院の目的
        targetReservation.setVisitReason(reservationRequest.getVisitReason());

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

    /**
     * 指定したペットの前回通院情報を取得する.
     * 
     * @param petId ペットID
     * @return 前回通院情報
     */
    public Optional<ReservationEntity> findLatestVisitByPetId(Long petId) {

        return reservationRepository
                .findTopByPet_IdAndAppointmentDateLessThanEqualOrderByAppointmentDateDesc(
                        petId,
                        LocalDate.now());
    }

    /**
     * 今日以降の予約一覧を取得する.
     *
     * @return 予約日の近い順に並んだ予約一覧
     */
    public List<ReservationEntity> findUpcomingReservations() {

        return reservationRepository
                .findByAppointmentDateGreaterThanEqualOrderByAppointmentDateAsc(
                        LocalDate.now());
    }
}
