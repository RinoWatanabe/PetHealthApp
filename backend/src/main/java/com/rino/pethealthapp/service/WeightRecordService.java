package com.rino.pethealthapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.entity.WeightRecordEntity;
import com.rino.pethealthapp.repository.WeightRecordRepository;

@Service
public class WeightRecordService {

    private final WeightRecordRepository weightRecordRepository;

    public WeightRecordService(WeightRecordRepository weightRecordRepository) {
        this.weightRecordRepository = weightRecordRepository;
    }

    /**
     * 指定したペットIDの体重一覧を取得する.
     *
     * @param petId ペットID
     * @return 体重一覧
     */
    public List<WeightRecordEntity> findAllByPetId(Long petId) {
        return weightRecordRepository.findByPet_Id(petId);
    }

    /**
     * 指定したIDの体重情報を取得する.
     *
     * @param id 体重ID
     * @return 体重情報
     */
    public Optional<WeightRecordEntity> findById(Long id) {
        return weightRecordRepository.findById(id);
    }

    /**
     * 体重を登録する.
     *
     * @param weightRecordEntity 登録する体重情報
     * @return 登録した体重情報
     */
    public WeightRecordEntity create(WeightRecordEntity weightRecordEntity) {
        return weightRecordRepository.save(weightRecordEntity);
    }

    /**
     * 指定したIDの体重情報を更新する.
     *
     * @param id                 体重ID
     * @param weightRecordEntity 更新する体重情報
     * @return 更新した体重情報
     */
    public WeightRecordEntity update(Long id, WeightRecordEntity weightRecordEntity) {

        WeightRecordEntity targetWeightRecord = weightRecordRepository.findById(id).orElseThrow();

        targetWeightRecord.setPet(weightRecordEntity.getPet());
        targetWeightRecord.setCheckDate(weightRecordEntity.getCheckDate());
        targetWeightRecord.setWeight(weightRecordEntity.getWeight());

        return weightRecordRepository.save(targetWeightRecord);
    }

    /**
     * 指定したIDの体重を削除する.
     *
     * @param id 体重ID
     */
    public void delete(Long id) {

        WeightRecordEntity targetWeightRecord = weightRecordRepository.findById(id).orElseThrow();

        weightRecordRepository.delete(targetWeightRecord);
    }

    /**
     * 指定したペットの最新体重情報を取得する.
     * 
     * @param petId ペットID
     * @return 最新体重情報
     */
    public Optional<WeightRecordEntity> findLatestWeightByPetId(Long petId) {

        return weightRecordRepository.findTopByPet_IdAndCheckDateLessThanEqualOrderByCheckDateDesc(
                petId,
                LocalDate.now());
    }
}
