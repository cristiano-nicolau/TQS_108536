package hw.tqs.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hw.tqs.model.Trip;
import hw.tqs.repository.TripRepository;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    @Autowired
    private TripRepository tripRepository;

    @PostConstruct
    public void init() {
        List<String> locations = List.of("Aveiro", "Braga", "Coimbra", "Porto", "Lisboa");
    
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
    
        List<Trip> trips = new ArrayList<>();
    
        Random random = new Random();
    
        for (String origin : locations) {
            for (String destination : locations) {
                if (!origin.equals(destination)) {
                    LocalDateTime departureDate = LocalDateTime.of(startDate, LocalTime.of(10, 0)); // Horário de partida inicial
                    while (departureDate.toLocalDate().isBefore(endDate)) {
                        for (int i = 0; i < 3; i++) { 
                            LocalTime randomDepartureTime = LocalTime.of(random.nextInt(7) + 10, random.nextInt(60)); // Horário de partida aleatório entre 10h e 17h59
                            LocalDateTime departureDateTime = LocalDateTime.of(departureDate.toLocalDate(), randomDepartureTime);
                            LocalDateTime arrivalDateTime = departureDateTime.plusHours(2 + random.nextInt(5)); // Duração da viagem entre 2 e 6 horas
                            Double price = Math.round((10 + Math.random() * 100) * 100.0) / 100.0;
                            
                            List<String> randomOccupiedSeats = new ArrayList<>();
                            for (int j = 0; j < random.nextInt(50) + 2; j++) {
                                randomOccupiedSeats.add("A" + (random.nextInt(50) + 1));
                            }
                            trips.add(new Trip(origin, destination, departureDate.toLocalDate(), randomDepartureTime, arrivalDateTime.toLocalTime(), price, 50, randomOccupiedSeats));
                        }
                        departureDate = departureDate.plusDays(1); 
                    }
                }
            }
        }
    
        tripRepository.saveAll(trips);
    }
    
}
