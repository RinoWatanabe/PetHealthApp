package com.rino.pethealthapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long>{

    List<NoteEntity> findByPet_Id(Long petId); 
}
