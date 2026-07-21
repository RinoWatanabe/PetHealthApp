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

import com.rino.pethealthapp.entity.NoteEntity;
import com.rino.pethealthapp.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * 指定したペットIDのひとこと一覧を取得する.
     * 
     * @param petId ペットID
     * @return ひとこと一覧
     */
    @GetMapping("/pets/{petId}/notes")
    public List<NoteEntity> findAllByPetId(@PathVariable Long petId) {
        return noteService.findAllByPetId(petId);
    }

    /**
     * 指定したIDのひとこと情報を取得する.
     * 
     * @param id ひとことID
     * @return ひとこと情報
     */
    @GetMapping("/notes/{id}")
    public Optional<NoteEntity> findById(@PathVariable Long id) {
        return noteService.findById(id);
    }

    /**
     * ひとことを登録する.
     * 
     * @param noteEntity 登録するひとこと情報
     * @return 登録したひとこと情報
     */
    @PostMapping("/notes")
    public NoteEntity create(@RequestBody NoteEntity noteEntity) {
        return noteService.create(noteEntity);
    }

    /**
     * 指定したIDのひとこと情報を更新する.
     * 
     * @param id         ひとことID
     * @param noteEntity 更新するひとこと情報
     * @return 更新したひとこと情報
     */
    @PutMapping("/notes/{id}")
    public NoteEntity update(
            @PathVariable Long id,
            @RequestBody NoteEntity noteEntity) {
        return noteService.update(id, noteEntity);
    }

    /**
     * 指定したIDのひとことを削除する.
     * 
     * @param id ひとことID
     */
    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable Long id) {

        noteService.delete(id);
    }
}
