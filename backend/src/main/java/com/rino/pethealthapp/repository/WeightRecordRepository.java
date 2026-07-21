package com.rino.pethealthapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.WeightRecordEntity;

public interface WeightRecordRepository extends JpaRepository<WeightRecordEntity, Long> {

    List<WeightRecordEntity> findByPet_Id(Long petId);

    // 指定したペットの体重記録から、今日以前のものを対象にして、記録日の新しい順で先頭の1件を取得する
    Optional<WeightRecordEntity> findTopByPet_IdAndCheckDateLessThanEqualOrderByCheckDateDesc(
            Long petId,
            LocalDate today);
}
