package com.rino.pethealthapp.dto;

import java.time.LocalDate;

public class UpcomingReservationResponse {

    private Long reservationId;
    private String petName;
    private LocalDate appointmentDate;
    private String hospitalName;
    private String visitReason;

    // コンストラクタ
    public UpcomingReservationResponse(Long reservationId, String petName, LocalDate appointmentDate,
            String hospitalName, String visitReason) {
        this.reservationId = reservationId;
        this.petName = petName;
        this.appointmentDate = appointmentDate;
        this.hospitalName = hospitalName;
        this.visitReason = visitReason;
    }

    // getter
    public Long getReservationId() {
        return reservationId;
    }

    public String getPetName() {
        return petName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getVisitReason() {
        return visitReason;
    }

}
