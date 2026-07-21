package com.rino.pethealthapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long>{

    List<NoteEntity> findByPet_Id(Long petId); 

    // 指定したペットのひとこと記録から、今日以前のものを対象にして、記録日の新しい順で先頭の1件を取得する
    Optional<NoteEntity> findTopByPet_IdAndRecordDateLessThanEqualOrderByRecordDateDesc(
            Long petId,
            LocalDate today);
}
