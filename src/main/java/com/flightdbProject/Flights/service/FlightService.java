package com.flightdbProject.Flights.service;

import com.flightdbProject.Flights.Flight;
import com.flightdbProject.Flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    // for sortinggg
    public Page<Flight> getFlightsPage(int page, int size) {
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.asc("flightId"))));
    }

    public void save(Flight flight) {
        flightRepository.save(flight);
    }


    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Page<Flight> searchFlightsByFlightId(Long flightId, int page, int size) {
        return flightRepository.findByFlightId(flightId, PageRequest.of(page, size));
    }

    public Flight findFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id: " + flightId));
    }

    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }

}
