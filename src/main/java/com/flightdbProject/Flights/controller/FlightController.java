package com.flightdbProject.Flights.controller;
import com.flightdbProject.Flights.Flight;

import com.flightdbProject.Flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

     //List in order and search
    @GetMapping("/flights/list")
    public String listFlights(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "150") int size,
                              @RequestParam(value = "flightId", required = false) Long flightId) {
        Page<Flight> flightsPage;
        if (flightId != null) {
            flightsPage = flightService.searchFlightsByFlightId(flightId, page, size);
        } else {
            flightsPage = flightService.getFlightsPage(page, size);
        }
        model.addAttribute("flights", flightsPage);
        model.addAttribute("flightId", flightId); // To keep the search query after redirect
        return "list";
    }

    //List flight dats
    @GetMapping("flights")
    public String homepage(Model model) {
        model.addAttribute("flight", new Flight());
        return "home";  
    }

    //Map about.html
    @GetMapping("about")
    public String aboutpage(Model model) {
        model.addAttribute("flight", new Flight());
        return "about";
    }


    // Map add.html
    @GetMapping("flights/add")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "add";  // returns the add.html template
    }

    //Map edit.html
    @GetMapping("flights/edit")
    public String showEditFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "edit";
    }

    // Get flight details for editing
    @GetMapping("/flights/edit/{id}")
    public String showEditForm(@PathVariable("id") Long flightId, Model model) {
        Flight flight = flightService.findFlightById(flightId);
        model.addAttribute("flight", flight);
        return "edit";
    }
    @PostMapping("/flights/update")
    public String updateFlight(@ModelAttribute Flight flight) {
        if (flight.getAircraftCode() == null || flight.getAircraftCode().isEmpty()) {
            return "redirect:/flights/edit/" + flight.getFlightId() + "?error=aircraftCodeRequired";
        }
        flightService.updateFlight(flight);
        return "redirect:/flights/list";
    }

    //Delete entry
   @PostMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights/list";
    }

    // Handle save for the save.html
    @PostMapping("flights/save")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.save(flight);
        return "redirect:/flights";
    }
}

