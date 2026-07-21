package com.rino.pethealthapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rino.pethealthapp.entity.NoteEntity;
import com.rino.pethealthapp.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
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
    public Optional<NoteEntity> findById(Long id) {
        return noteRepository.findById(id);
    }

    /**
     * ひとことを登録する.
     * 
     * @param noteEntity 登録するひとこと情報
     * @return 登録したひとこと情報
     */
    public NoteEntity create(NoteEntity noteEntity) {
        return noteRepository.save(noteEntity);
    }

    /**
     * 指定したIDのひとこと情報を更新する.
     *
     * @param id         ひとことID
     * @param noteEntity 更新するひとこと情報
     * @return 更新したひとこと情報
     */
    public NoteEntity update(Long id, NoteEntity noteEntity) {

        NoteEntity targetNote = noteRepository.findById(id).orElseThrow();

        targetNote.setPet(noteEntity.getPet());
        targetNote.setRecordDate(noteEntity.getRecordDate());
        targetNote.setNote(noteEntity.getNote());

        return noteRepository.save(targetNote);
    }

    /**
     * 指定したIDのひとことを削除する.
     * 
     * @param id ひとことID
     */
    public void delete(Long id) {

        NoteEntity targetNote = noteRepository.findById(id).orElseThrow();

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
