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


    // Method to fetch paginated and sorted flights
    public Page<Flight> getFlightsPage(int page, int size) {
        // Sort flights by flightId in ascending order
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.asc("flightId"))));
    }

    public void save(Flight flight) {
        flightRepository.save(flight);
    }
   public void deleteFlight(Long id) {
    flightRepository.deleteById(id);  // Assuming you are using Spring Data JPA
}

}
