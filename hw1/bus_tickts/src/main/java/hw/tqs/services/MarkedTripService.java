package hw.tqs.services;

import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import hw.tqs.model.MarkedTrip;
import hw.tqs.repository.MarkedTripRepository;

@Service
public class MarkedTripService {
    
    @Autowired
    private MarkedTripRepository markedTripRepository;

    public MarkedTrip saveMarkedTrip(MarkedTrip markedTrip) {
        return markedTripRepository.save(markedTrip);
    }

    public void deleteMarkedTrip(Long id) {
        markedTripRepository.deleteById(id);
    }

    public MarkedTrip updateMarkedTrip(Long id, MarkedTrip markedTrip) {
        markedTripRepository.deleteById(id);
        return markedTripRepository.save(markedTrip);
    }

    public boolean existsMarkedTrip(Long id) {
        return markedTripRepository.existsById(id);
    }

    public List<MarkedTrip> getMarkedTrips() {
        return markedTripRepository.findAll();
    }

    public MarkedTrip getMarkedTripById(Long id) {
        return markedTripRepository.findById(id);
    }

    public List<MarkedTrip> getMarkedTripsByTripID(Integer tripID) {
        return markedTripRepository.findByTripID(tripID);
    }

    
}
