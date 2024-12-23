package com.example.spring_boot.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.spring_boot.models.Notes;
import com.example.spring_boot.repository.NotesRepositoryImpl;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepositoryImpl repository;

    public NotesServiceImpl(NotesRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Notes getNote(long userId, LocalDate date) {
        return repository.getNote(userId, date);
    }


    @Override
    public void updateNote(long userId, LocalDate date, String details) {
        repository.updateNote(userId, date, details);
    }

    @Override
    public void addNote(long userId, LocalDate date, String details) {
        repository.addNote(userId, date, details);
    }



    
}
