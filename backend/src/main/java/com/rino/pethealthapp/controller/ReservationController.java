package com.rino.pethealthapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rino.pethealthapp.dto.request.ReservationRequest;
import com.rino.pethealthapp.entity.ReservationEntity;
import com.rino.pethealthapp.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * 指定したペットIDの予約一覧を取得する.
     * 
     * @param petId ペットID
     * @return 予約一覧
     */
    @GetMapping("/pets/{petId}/reservations")
    public List<ReservationEntity> findAllByPetId(@PathVariable Long petId) {
        return reservationService.findAllByPetId(petId);
    }

    /**
     * 指定したIDの予約情報を取得する.
     * 
     * @param id 予約ID
     * @return 予約情報
     */
    @GetMapping("/reservations/{id}")
    public ReservationEntity findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    /**
     * 予約を登録する.
     * 
     * @param reservationRequest 登録する予約情報
     * @return 登録した予約の情報
     */
    @PostMapping("/reservations")
    public ReservationEntity create(
            @Valid @RequestBody ReservationRequest reservationRequest) {
        return reservationService.create(reservationRequest);
    }

    /**
     * 指定したIDの予約情報を更新する.
     * 
     * @param id                 予約ID
     * @param reservationRequest 更新する予約情報
     * @return 更新した予約情報
     */
    @PutMapping("/reservations/{id}")
    public ReservationEntity update(
            @PathVariable Long id,
            @Valid @RequestBody ReservationRequest reservationRequest) {
        return reservationService.update(id, reservationRequest);
    }

    /**
     * 指定したIDの予約を削除する.
     * 
     * @param id 予約ID
     */
    @DeleteMapping("/reservations/{id}")
    public void delete(@PathVariable Long id) {
        reservationService.delete(id);
    }

}
