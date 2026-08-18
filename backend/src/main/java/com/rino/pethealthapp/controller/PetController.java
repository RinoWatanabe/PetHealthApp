package com.rino.pethealthapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rino.pethealthapp.dto.request.PetRequest;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * ペット一覧を取得する.
     * 
     * @return ペット一覧
     */
    @GetMapping("/pets")
    public List<PetEntity> findAll() {
        return petService.findAll();
    }

    /**
     * 指定したIDのペット情報を取得する.
     * 
     * @param id ペットID
     * @return ペット情報
     */
    @GetMapping("/pets/{id}")
    public PetEntity findById(@PathVariable Long id) {
        return petService.findById(id);
    }

    /**
     * ペットを登録する.
     * 
     * @param petRequest 登録するペット情報
     * @return 登録したペットの情報
     */
    @PostMapping("/pets")
    public PetEntity create(
            @Valid @RequestBody PetRequest petRequest) {
        return petService.create(petRequest);
    }

    /**
     * 指定したIDのペット情報を更新する.
     * 
     * @param id        ペットID
     * @param petRequest 更新するペット情報
     * @return 更新したペットの情報
     */
    @PutMapping("/pets/{id}")
    public PetEntity update(
            @PathVariable Long id,
            @Valid @RequestBody PetRequest petRequest) {
        return petService.update(id, petRequest);
    }

}
