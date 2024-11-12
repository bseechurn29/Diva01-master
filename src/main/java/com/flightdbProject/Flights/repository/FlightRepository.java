package com.flightdbProject.Flights.repository;

import com.flightdbProject.Flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  FlightRepository extends JpaRepository<Flight, Long> {


}
