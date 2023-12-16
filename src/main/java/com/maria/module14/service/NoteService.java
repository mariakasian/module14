package com.maria.module14.service;

import com.maria.module14.exceptions.NoteNotFoundException;
import com.maria.module14.entity.Note;
import com.maria.module14.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional
    public Note add(Note note) {

        return noteRepository.save(note);
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note getById(Long id) throws NoteNotFoundException{
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    @Transactional
    public void update(Long id, String newTitle, String newContent) throws NoteNotFoundException {
        Note updatingNote = getById(id);
        if (updatingNote == null){
            throw new NoteNotFoundException(id);
        }

        updatingNote.setTitle(newTitle);
        updatingNote.setContent(newContent);
        noteRepository.save(updatingNote);
    }

    @Transactional
    public void deleteById(Long id) throws NoteNotFoundException {
        if (getById(id) == null){
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }

    @PostConstruct
    public void init() throws NoteNotFoundException {
        Note n1 = new Note();
        n1.setTitle("Note #1");
        n1.setContent("Content #1");
        add(n1);

        Note n2 = new Note();
        n2.setTitle("Note #2");
        n2.setContent("Content #2");
        add(n2);

        System.out.println("=============================");
        System.out.println("Getting by Id:");
        System.out.println("getById(n1.getId()) = " + getById(n1.getId()));

        System.out.println("=============================");
        System.out.println("List All Notes:");
        System.out.println("listAll() = " + listAll());

        System.out.println("=============================");
        System.out.println("Updating the Note #1");
        update(n1.getId(), "Updated Note #1", "Updated content #1");
        System.out.println("getById(n1.getId()) = " + getById(n1.getId()));

        System.out.println("=============================");
        System.out.println("Deleting by Id the Note #2");
        deleteById(n2.getId());
        System.out.println("listAll() = " + listAll());
        System.out.println("=============================");
    }
}
