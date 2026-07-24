package com.rino.pethealthapp.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class NoteRequest {

    // ペットID
    @NotNull(message = "ペットを選択してください。")
    private Long petId;

    // 記録日
    @NotNull(message = "記録日を選択してください。")
    @PastOrPresent(message = "本日以前の日付を選択してください。")
    private LocalDate recordDate;

    // ひとこと
    @NotBlank(message = "ひとことを入力してください。")
    @Size(max = 200, message = "ひとことは200文字以内で入力してください。")
    private String note;

    // ====================================
    // 引数なしコンストラクタ
    public NoteRequest() {
    }

    // getter / setter
    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
