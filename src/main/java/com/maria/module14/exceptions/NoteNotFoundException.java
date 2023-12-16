package com.maria.module14.exceptions;

public class NoteNotFoundException extends Exception {
    private static final String NOTE_NOT_FOUND_EXCEPTION_TEXT = "Note with id = %s is not found.";

    public NoteNotFoundException(Long noteId) {
        super(String.format(NOTE_NOT_FOUND_EXCEPTION_TEXT, noteId));
    }
}

