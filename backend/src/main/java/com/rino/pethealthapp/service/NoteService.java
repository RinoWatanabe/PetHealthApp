package com.rino.pethealthapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.dto.request.NoteRequest;
import com.rino.pethealthapp.entity.NoteEntity;
import com.rino.pethealthapp.entity.PetEntity;
import com.rino.pethealthapp.exception.ResourceNotFoundException;
import com.rino.pethealthapp.repository.NoteRepository;
import com.rino.pethealthapp.repository.PetRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final PetRepository petRepository;

    public NoteService(
            NoteRepository noteRepository,
            PetRepository petRepository) {
        this.noteRepository = noteRepository;
        this.petRepository = petRepository;
    }

    /**
     * 指定したペットIDのひとこと一覧を取得する.
     * 
     * @param petId ペットID
     * @return ひとこと一覧
     */
    public List<NoteEntity> findAllByPetId(Long petId) {
        return noteRepository.findByPet_Id(petId);
    }

    /**
     * 指定したIDのひとこと情報を取得する.
     * 
     * @param id ひとことID
     * @return ひとこと情報
     */
    public NoteEntity findById(Long id) {
        return noteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ひとこと情報が見つかりません"));
    }

    /**
     * ひとことを登録する.
     * 
     * @param noteRequest 登録するひとこと情報
     * @return 登録したひとこと情報
     */
    public NoteEntity create(NoteRequest noteRequest) {

        NoteEntity noteEntity = new NoteEntity();

        // （1）ペットIDからPetEntityを取得
        Long petId = noteRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("ペットが見つかりません"));

        noteEntity.setPet(pet);

        // （2）記録日
        noteEntity.setRecordDate(noteRequest.getRecordDate());

        // （3）ひとこと
        noteEntity.setNote(noteRequest.getNote());

        return noteRepository.save(noteEntity);
    }

    /**
     * 指定したIDのひとこと情報を更新する.
     *
     * @param id         ひとことID
     * @param noteRequest 更新するひとこと情報
     * @return 更新したひとこと情報
     */
    public NoteEntity update(Long id, NoteRequest noteRequest) {

        NoteEntity targetNote = noteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ひとこと情報が見つかりません"));

        // （1）ペットIDからPetEntityを取得
        Long petId = noteRequest.getPetId();

        PetEntity pet = petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("ペットが見つかりません"));

        targetNote.setPet(pet);

        // （2）記録日
        targetNote.setRecordDate(noteRequest.getRecordDate());

        // （3）ひとこと
        targetNote.setNote(noteRequest.getNote());

        return noteRepository.save(targetNote);
    }

    /**
     * 指定したIDのひとことを削除する.
     * 
     * @param id ひとことID
     */
    public void delete(Long id) {

        NoteEntity targetNote = noteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ひとこと情報が見つかりません"));

        noteRepository.delete(targetNote);

    }

    /**
     * 指定したペットの最新のひとこと記録を取得する.
     * 
     * @param petId ペットID
     * @return 最新ひとこと記録
     */
    public Optional<NoteEntity> findLatestNoteByPetId(Long petId) {

        return noteRepository.findTopByPet_IdAndRecordDateLessThanEqualOrderByRecordDateDesc(
                petId,
                LocalDate.now());
    }

}
