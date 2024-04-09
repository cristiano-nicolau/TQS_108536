package hw.tqs.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import hw.tqs.model.Trip;
import hw.tqs.repository.TripRepository;

@DataJpaTest
public class TripRepositoryTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByOriginAndDestination_thenReturnTrip() {
        Trip trip = new Trip("Porto", "Lisboa", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), 50.0, 50,
                List.of("A12", "A20", "A30", "A40", "A25"));
        entityManager.persist(trip);
        entityManager.flush();
        List<Trip> found = tripRepository.findByOriginAndDestination("Porto", "Lisboa");
        assertThat(found.get(0).getOrigin()).isEqualTo(trip.getOrigin());
        assertThat(found.get(0).getDestination()).isEqualTo(trip.getDestination());
    }

    @Test
    @Disabled
    public void whenFindByOriginAndDestinationAndDepartureDate_thenReturnTrip() {
        Trip trip = new Trip("Porto", "Lisboa", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), 50.0, 50,
                List.of("A12", "A20", "A30", "A40", "A25"));
        entityManager.persist(trip);
        entityManager.flush();
        List<Trip> found = tripRepository.findByOriginAndDestinationAndDepartureDate("Porto", "Lisboa", LocalDate.now());
        assertThat(found.get(0).getOrigin()).isEqualTo(trip.getOrigin());
        assertThat(found.get(0).getDestination()).isEqualTo(trip.getDestination());
        assertThat(found.get(0).getDepartureDate()).isEqualTo(trip.getDepartureDate());
    }

    @Test
    @Disabled
    public void whenExistsById_thenReturnTrue() {
        Trip trip = new Trip("Porto", "Lisboa", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), 50.0, 50,
                List.of("A12", "A20", "A30", "A40", "A25"));
        entityManager.persist(trip);
        entityManager.flush();
        boolean found = tripRepository.existsById(trip.getId());
        assertThat(found).isEqualTo(true);
    }

}

