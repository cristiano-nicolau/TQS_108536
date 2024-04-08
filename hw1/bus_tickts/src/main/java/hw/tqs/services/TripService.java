package hw.tqs.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hw.tqs.model.Trip;
import hw.tqs.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;
    

    public List<Trip> getTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Integer id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.orElse(null);
    }

    public List<Trip> getTripsByOriginAndDestination(String origin, String destination) {
        return tripRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Trip> getTripsByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDate departureDate) {
        return tripRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    
}
