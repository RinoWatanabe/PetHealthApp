package com.rino.pethealthapp.dto;

public class PetSummaryResponse {

    private Long petId;
    private String petName;

    private LastVisitResponse lastVisit;
    private LatestWeightResponse latestWeight;
    private LatestNoteResponse latestNote;

    // コンストラクタ
    public PetSummaryResponse(
            Long petId,
            String petName,
            LastVisitResponse lastVisit,
            LatestWeightResponse latestWeight,
            LatestNoteResponse latestNote) {

        this.petId = petId;
        this.petName = petName;
        this.lastVisit = lastVisit;
        this.latestWeight = latestWeight;
        this.latestNote = latestNote;
    }

    // getter
    public Long getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public LastVisitResponse getLastVisit() {
        return lastVisit;
    }

    public LatestWeightResponse getLatestWeight() {
        return latestWeight;
    }

    public LatestNoteResponse getLatestNote() {
        return latestNote;
    }
}
