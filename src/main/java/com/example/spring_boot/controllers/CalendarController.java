package com.example.spring_boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.format.DateTimeFormatter;


import com.example.spring_boot.models.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;

import com.example.spring_boot.service.*;

@Controller
@RequestMapping("/")
public class CalendarController {

    private final CalendarServiceImpl service;

    @Autowired
    public CalendarController(CalendarServiceImpl service) {
        this.service = service;
    }

	@GetMapping("")
    public String defaultCalendar(Model model) {
        return newMonth(model, 0);
    }

	@GetMapping("calendar/{monthOffset}")
	public String newMonth(Model model, @PathVariable int monthOffset) {

		LocalDate localDate = LocalDate.now().plusMonths(monthOffset);
		int currentDay;

		if (monthOffset == 0) {
			currentDay = LocalDate.now().getDayOfMonth();
		}
		else {
			currentDay = 32;
		}

		int daysInMonth = localDate.lengthOfMonth();
		int firstDayOfMonth = localDate.withDayOfMonth(1).getDayOfWeek().getValue();

		Collection<Event> events = service.getEventForMonth(localDate);
		model.addAttribute("localDate", localDate);
		model.addAttribute("currentDay", currentDay);
		model.addAttribute("events", events);
		model.addAttribute("daysInMonth", daysInMonth);
		model.addAttribute("firstDayOfMonth", firstDayOfMonth);
		model.addAttribute("monthOffset", monthOffset);

		return "calendar";
	}

	@GetMapping("/events/{eventId}")
	public String event(Model model, @PathVariable Long eventId) {

		Event event = service.getEvent(eventId);

		model.addAttribute("event", event);
		return "event";
	}

	@GetMapping("/events/add")
	public String addEvent(@RequestParam LocalDate date, Model model) {

	
		Event event = new Event();
		event.setDate(date);
		event.setStartTime(LocalTime.of(0,0));
		event.setEndTime(LocalTime.of(11, 59));

		model.addAttribute("event", event);
	
		return "add-event";
	}

	@PostMapping("/events/add")
	public String postEvent(@ModelAttribute("event") Event event) {

		int currMonth = LocalDate.now().getMonthValue();
		int currYear = LocalDate.now().getYear();
		int eventMonth = event.getDate().getMonthValue();
		int eventYear = event.getDate().getYear();

		int monthOffset = 0;

		if (eventYear > currYear) {
			monthOffset = (eventMonth - currMonth) + 12;
		}
		else if (currYear > eventYear) {
			monthOffset = (eventMonth - currMonth) - 12;
		}
		else {
			monthOffset = eventMonth - currMonth;
		}
		
		service.addEvent(1L, event.getTitle(), event.getDate(), event.getStartTime(), event.getEndTime(), event.getDetails());

		return "redirect:/calendar/" + monthOffset;
	}

}
