package com.example.spring_boot.repository;
import com.example.spring_boot.models.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public interface NotesRepository {

    public Notes getNote(long userId, LocalDate date);
    
}
