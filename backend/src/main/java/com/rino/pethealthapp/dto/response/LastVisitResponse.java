package com.rino.pethealthapp.dto.response;

import java.time.LocalDate;

public class LastVisitResponse {

    private LocalDate appointmentDate;
    private String hospitalName;
    private String visitReason;

    // コンストラクタ
    public LastVisitResponse(LocalDate appointmentDate, String hospitalName, String visitReason) {
        this.appointmentDate = appointmentDate;
        this.hospitalName = hospitalName;
        this.visitReason = visitReason;
    }

    // getter
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
