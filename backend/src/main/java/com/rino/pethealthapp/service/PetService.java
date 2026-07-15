package com.rino.pethealthapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.entity.PetEntity;
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
    public Optional<PetEntity> findById(Long id) {
        return petRepository.findById(id);
    }

    /**
     * ペットを登録する.
     * 
     * @param petEntity 登録するペット情報
     * @return 登録したペットの情報
     */
    public PetEntity create(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    /**
     * 指定したIDのペット情報を更新する.
     * 
     * @param id ペットID
     * @param petEntity 更新するペット情報
     * @return 更新したペットの情報
     */
    public PetEntity update(Long id, PetEntity petEntity) {
        
        // ↓ 学習用メモ
        // Repositoryへ指示（まずID指定で情報をとってきてもらう）
        PetEntity targetPet = petRepository.findById(id).orElseThrow();

        // ↓ 学習用メモ
        // ここで画面から受け取った情報に更新する
        targetPet.setName(petEntity.getName());
        targetPet.setPetType(petEntity.getPetType());
        targetPet.setCustomPetType(petEntity.getCustomPetType());
        targetPet.setAge(petEntity.getAge());
        targetPet.setGender(petEntity.getGender());
        targetPet.setBirthday(petEntity.getBirthday());

        // ↓ 学習用メモ
        // Repositoryへ更新したペット情報を保存するよう依頼する
        return petRepository.save(targetPet);
    }
}
