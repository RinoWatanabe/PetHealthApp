package com.rino.pethealthapp.dto.response;

import java.time.LocalDate;

public class LatestNoteResponse {

    private Long noteId;
    private LocalDate recordDate;
    private String note;

    // コンストラクタ
    public LatestNoteResponse(Long noteId, LocalDate recordDate, String note) {
        this.noteId = noteId;
        this.recordDate = recordDate;
        this.note = note;
    }

    // getter
    public Long getNoteId() {
        return noteId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public String getNote() {
        return note;
    }

}
