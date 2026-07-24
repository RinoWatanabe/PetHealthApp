package com.rino.pethealthapp.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReservationRequest {

    // ペットID
    @NotNull(message = "ペットを選択してください。")
    private Long petId;

    // 予約日
    @NotNull(message = "予約日を選択してください。")
    @FutureOrPresent(message = "本日以降の日付を選択してください。")
    private LocalDate appointmentDate;

    // 病院名
    @NotBlank(message = "病院名を入力してください。")
    @Size(max = 20, message = "病院名は20文字以内で入力してください。")
    private String hospitalName;

    // 通院の目的
    @NotBlank(message = "通院の目的を入力してください。")
    @Size(max = 50, message = "通院の目的は50文字以内で入力してください。")
    private String visitReason;

    // ====================================
    // 引数なしコンストラクタ
    public ReservationRequest() {
    }

    // getter / setter
    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

}
