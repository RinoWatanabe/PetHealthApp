package com.rino.pethealthapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rino.pethealthapp.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long>{

}
