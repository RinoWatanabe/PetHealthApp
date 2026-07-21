package com.rino.pethealthapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LatestWeightResponse {

    private Long weightRecordId;
    private LocalDate checkDate;
    private BigDecimal weight;

    // コンストラクタ
    public LatestWeightResponse(Long weightRecordId, LocalDate checkDate, BigDecimal weight) {
        this.weightRecordId = weightRecordId;
        this.checkDate = checkDate;
        this.weight = weight;
    }

    // getter
    public Long getWeightRecordId() {
        return weightRecordId;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }



    
}
