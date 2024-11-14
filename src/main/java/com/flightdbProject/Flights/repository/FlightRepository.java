package com.flightdbProject.Flights.repository;

import com.flightdbProject.Flights.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  FlightRepository extends JpaRepository<Flight, Long> {


        Page<Flight> findByFlightId(Long flightId, Pageable pageable);


}
