package com.rino.pethealthapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.dto.request.PetRequest;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.exception.ResourceNotFoundException;
import com.rino.pethealthapp.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * ペット一覧を取得する.
     * 
     * @return ペット一覧
     */
    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    /**
     * 指定したIDのペット情報を取得する.
     * 
     * @param id ペットID
     * @return ペット情報
     */
    public PetEntity findById(Long id) {
        return petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ペットが見つかりません"));
    }

    /**
     * ペットを登録する.
     * 
     * @param petRequest 登録するペット情報
     * @return 登録したペットの情報
     */
    public PetEntity create(PetRequest petRequest) {

        PetEntity petEntity = new PetEntity();

        petEntity.setName(petRequest.getPetName());
        petEntity.setPetType(petRequest.getPetType());
        petEntity.setCustomPetType(petRequest.getCustomPetType());
        petEntity.setAge(petRequest.getAge());
        petEntity.setGender(petRequest.getGender());
        petEntity.setBirthday(petRequest.getBirthday());

        return petRepository.save(petEntity);
    }

    /**
     * 指定したIDのペット情報を更新する.
     * 
     * @param id ペットID
     * @param petRequest 更新するペット情報
     * @return 更新したペットの情報
     */
    public PetEntity update(Long id, PetRequest petRequest) {
        
        // ↓ 学習用メモ
        // Repositoryへ指示（まずID指定で情報をとってきてもらう）
        PetEntity targetPet = petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ペットが見つかりません"));

        // ↓ 学習用メモ
        // ここで画面から受け取った情報に更新する
        targetPet.setName(petRequest.getPetName());
        targetPet.setPetType(petRequest.getPetType());
        targetPet.setCustomPetType(petRequest.getCustomPetType());
        targetPet.setAge(petRequest.getAge());
        targetPet.setGender(petRequest.getGender());
        targetPet.setBirthday(petRequest.getBirthday());

        // ↓ 学習用メモ
        // Repositoryへ更新したペット情報を保存するよう依頼する
        return petRepository.save(targetPet);
    }
}
