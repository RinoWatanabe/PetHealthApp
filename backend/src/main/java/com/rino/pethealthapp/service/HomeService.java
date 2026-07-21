package com.rino.pethealthapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.dto.HomeResponse;
import com.rino.pethealthapp.dto.LastVisitResponse;
import com.rino.pethealthapp.dto.LatestNoteResponse;
import com.rino.pethealthapp.dto.LatestWeightResponse;
import com.rino.pethealthapp.dto.PetSummaryResponse;
import com.rino.pethealthapp.dto.UpcomingReservationResponse;
import com.rino.pethealthapp.entity.NoteEntity;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.entity.ReservationEntity;
import com.rino.pethealthapp.entity.WeightRecordEntity;

@Service
public class HomeService {

        private final ReservationService reservationService;
        private final PetService petService;
        private final WeightRecordService weightRecordService;
        private final NoteService noteService;

        public HomeService(
                        ReservationService reservationService,
                        PetService petService,
                        WeightRecordService weightRecordService,
                        NoteService noteService) {

                this.reservationService = reservationService;
                this.petService = petService;
                this.weightRecordService = weightRecordService;
                this.noteService = noteService;
        }

        public HomeResponse getHome() {

                // ペット一覧取得する
                List<PetEntity> pets = petService.findAll();

                // 全ペット分のホーム表示情報を入れるリスト
                List<PetSummaryResponse> petSummaries = new ArrayList<>();

                // 1匹ごとの前回通院・最新体重・最新ひとことを取得する
                for (PetEntity pet : pets) {

                        // (1)-1 前回通院
                        Optional<ReservationEntity> latestVisit = reservationService
                                        .findLatestVisitByPetId(pet.getId());

                        // (2)-1: 最新の体重記録
                        Optional<WeightRecordEntity> latestWeight = weightRecordService
                                        .findLatestWeightByPetId(pet.getId());

                        // (3)-1: 最新のひとこと記録
                        Optional<NoteEntity> latestNote = noteService.findLatestNoteByPetId(pet.getId());

                        // ============================
                        // (1)-2: 前回通院をDTOへ変換する
                        LastVisitResponse lastVisitResponse = latestVisit
                                        .map(reservation -> new LastVisitResponse(
                                                        reservation.getAppointmentDate(),
                                                        reservation.getHospital().getHospitalName(),
                                                        reservation.getVisitReason()))
                                        .orElse(null);

                        // (2)-2: 最新の体重記録をDTOへ変換する
                        LatestWeightResponse latestWeightResponse = latestWeight
                                        .map(weight -> new LatestWeightResponse(
                                                        weight.getId(),
                                                        weight.getCheckDate(),
                                                        weight.getWeight()))
                                        .orElse(null);

                        // (3)-2: 最新のひとこと記録をDTOへ変換する
                        LatestNoteResponse latestNoteResponse = latestNote
                                        .map(note -> new LatestNoteResponse(
                                                        note.getId(),
                                                        note.getRecordDate(),
                                                        note.getNote()))
                                        .orElse(null);

                        // ============================
                        // 1匹分の情報をまとめる

                        PetSummaryResponse petSummaryResponse = new PetSummaryResponse(
                                        pet.getId(),
                                        pet.getName(),
                                        lastVisitResponse,
                                        latestWeightResponse,
                                        latestNoteResponse);

                        // 全ペット分のリストへ追加する
                        petSummaries.add(petSummaryResponse);

                }

                // ============================
                // 近日の予約一覧を取得する
                List<ReservationEntity> upcomingReservationEntities = reservationService
                                .findUpcomingReservations();

                // 近日の予約情報をDTOへ変換する
                List<UpcomingReservationResponse> upcomingReservations = new ArrayList<>();

                for (ReservationEntity reservation : upcomingReservationEntities) {

                        UpcomingReservationResponse upcomingReservation =

                                new UpcomingReservationResponse(
                                        reservation.getId(),
                                        reservation.getPet().getName(),
                                        reservation.getAppointmentDate(),
                                        reservation.getHospital().getHospitalName(),
                                        reservation.getVisitReason());

                        upcomingReservations.add(upcomingReservation);
                }

                return new HomeResponse(
                                upcomingReservations, 
                                petSummaries);

        }

}
