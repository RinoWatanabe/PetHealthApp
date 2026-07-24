package com.rino.pethealthapp.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class WeightRecordRequest {

    // ペットID
    @NotNull(message = "ペットを選択してください。")
    private Long petId;

    // 測定日
    @NotNull(message = "測定日を選択してください。")
    @PastOrPresent(message = "本日以前の日付を選択してください。")
    private LocalDate checkDate;

    // 体重
    @NotNull(message = "体重を入力してください。")
    @DecimalMin(value = "0.0", inclusive = false, message = "体重は0より大きく100以下で入力してください。")
    @DecimalMax(value = "100.0", message = "体重は0より大きく100以下で入力してください。")
    @Digits(integer = 3, fraction = 1, message = "体重は小数点第1位までで入力してください。")
    private BigDecimal weight;

    // ====================================
    // 引数なしコンストラクタ
    public WeightRecordRequest() {
    }

    // getter / setter
    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

}
