package com.rino.pethealthapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rino.pethealthapp.dto.request.WeightRecordRequest;
import com.rino.pethealthapp.entity.WeightRecordEntity;
import com.rino.pethealthapp.service.WeightRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class WeightRecordController {

    private final WeightRecordService weightRecordService;

    public WeightRecordController(WeightRecordService weightRecordService) {
        this.weightRecordService = weightRecordService;
    }

    /**
     * 指定したペットIDの体重一覧を取得する.
     *
     * @param petId ペットID
     * @return 体重一覧
     */
    @GetMapping("/pets/{petId}/weight-records")
    public List<WeightRecordEntity> findAllByPetId(@PathVariable Long petId) {
        return weightRecordService.findAllByPetId(petId);
    }

    /**
     * 指定したIDの体重情報を取得する.
     *
     * @param id 体重ID
     * @return 体重情報
     */
    @GetMapping("/weight-records/{id}")
    public Optional<WeightRecordEntity> findById(@PathVariable Long id) {
        return weightRecordService.findById(id);
    }

    /**
     * 体重を登録する.
     *
     * @param weightRecordRequest 登録する体重情報
     * @return 登録した体重情報
     */
    @PostMapping("/weight-records")
    public WeightRecordEntity create(
            @Valid @RequestBody WeightRecordRequest weightRecordRequest) {
        return weightRecordService.create(weightRecordRequest);
    }

    /**
     * 指定したIDの体重情報を更新する.
     *
     * @param id                  体重ID
     * @param weightRecordRequest 更新する体重情報
     * @return 更新した体重情報
     */
    @PutMapping("/weight-records/{id}")
    public WeightRecordEntity update(
            @PathVariable Long id,
            @Valid @RequestBody WeightRecordRequest weightRecordRequest) {
        return weightRecordService.update(id, weightRecordRequest);
    }

    /**
     * 指定したIDの体重を削除する.
     *
     * @param id 体重ID
     */
    @DeleteMapping("/weight-records/{id}")
    public void delete(@PathVariable Long id) {
        weightRecordService.delete(id);
    }
}
