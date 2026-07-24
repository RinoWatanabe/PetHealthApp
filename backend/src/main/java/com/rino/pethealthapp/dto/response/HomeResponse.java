package com.rino.pethealthapp.dto.response;

import java.util.List;

public class HomeResponse {

    private List<UpcomingReservationResponse> upcomingReservations;

    private List<PetSummaryResponse> petSummaries;

    // コンストラクタ
    public HomeResponse(List<UpcomingReservationResponse> upcomingReservations, List<PetSummaryResponse> petSummaries) {
        this.upcomingReservations = upcomingReservations;
        this.petSummaries = petSummaries;
    }

    // getter
    public List<UpcomingReservationResponse> getUpcomingReservations() {
        return upcomingReservations;
    }

    public List<PetSummaryResponse> getPetSummaries() {
        return petSummaries;
    }

}
