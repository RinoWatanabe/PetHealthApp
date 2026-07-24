package com.rino.pethealthapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.dto.request.WeightRecordRequest;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.entity.WeightRecordEntity;
import com.rino.pethealthapp.repository.PetRepository;
import com.rino.pethealthapp.repository.WeightRecordRepository;

@Service
public class WeightRecordService {

    private final WeightRecordRepository weightRecordRepository;
    private final PetRepository petRepository;

    public WeightRecordService(
            WeightRecordRepository weightRecordRepository,
            PetRepository petRepository) {
        this.weightRecordRepository = weightRecordRepository;
        this.petRepository = petRepository;
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
     * @param weightRecordRequest 登録する体重情報
     * @return 登録した体重情報
     */
    public WeightRecordEntity create(WeightRecordRequest weightRecordRequest) {

        WeightRecordEntity weightRecordEntity = new WeightRecordEntity();

        // (1) ペットIDからPetEntityを取得
        Long petId = weightRecordRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow();

        weightRecordEntity.setPet(pet);

        // （2）測定日
        weightRecordEntity.setCheckDate(weightRecordRequest.getCheckDate());

        // （3）体重
        weightRecordEntity.setWeight(weightRecordRequest.getWeight());

        return weightRecordRepository.save(weightRecordEntity);
    }

    /**
     * 指定したIDの体重情報を更新する.
     *
     * @param id                  体重ID
     * @param weightRecordRequest 更新する体重情報
     * @return 更新した体重情報
     */
    public WeightRecordEntity update(Long id, WeightRecordRequest weightRecordRequest) {

        WeightRecordEntity targetWeightRecord = weightRecordRepository.findById(id).orElseThrow();

        // （1）ペットIDからPetEntityを取得
        Long petId = weightRecordRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow();

        targetWeightRecord.setPet(pet);

        // （2）測定日
        targetWeightRecord.setCheckDate(weightRecordRequest.getCheckDate());

        // （3）体重
        targetWeightRecord.setWeight(weightRecordRequest.getWeight());

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
