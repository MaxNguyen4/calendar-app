package com.example.spring_boot.repository;
import com.example.spring_boot.models.Event;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public interface EventRepository {
    public List<Event> getAllEvents();
    public List<Event> getEventsByUserId(Long id);
    public Event getEvent(Long id);
    public Long addEvent(Long userId, String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details);
    public void updateEvent(Long id, String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details);
    public void deleteEvent(Long id);

    //Long userId, String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details) {
    
}
