package com.flightdbProject.Flights.controller;
import com.flightdbProject.Flights.Flight;
import com.flightdbProject.Flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights/list")
    public String listFlights(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "150") int size) {
        Page<Flight> flightsPage = flightService.getFlightsPage(page, size);
        model.addAttribute("flights", flightsPage);
        return "list";
    }
    @GetMapping("flights")
    public String homepage(Model model) {
        model.addAttribute("flight", new Flight());
        return "home";  // returns the add.html template
    }


    // GET: Display the "Add Flight" form
    @GetMapping("flights/add")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "add";  // returns the add.html template
    }

    // POST: Handle form submission and save the flight
    @PostMapping("flights/save")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.save(flight);
        return "redirect:/flights";
    }
}
