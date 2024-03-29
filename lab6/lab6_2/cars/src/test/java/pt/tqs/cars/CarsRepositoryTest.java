package pt.tqs.cars;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.tqs.cars.CarModel;
import pt.tqs.cars.CarRepository;

@DataJpaTest
public class CarsRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindById_thenReturnCar() {
        CarModel audi = new CarModel("Audi", "A4");
        entityManager.persistAndFlush(audi);

        CarModel found = carRepository.findByCarId(audi.getcarId());

        assertThat(found).isNotNull();
        assertThat(found.getMaker()).isEqualTo(audi.getMaker());
        assertThat(found.getModel()).isEqualTo(audi.getModel());   
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        CarModel fromDb = carRepository.findByCarId(-11L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        CarModel audi = new CarModel("Audi", "A4");
        CarModel bmw = new CarModel("BMW", "X1");
        CarModel mercedes = new CarModel("Mercedes", "C180");

        entityManager.persist(audi);
        entityManager.persist(bmw);
        entityManager.persist(mercedes);
        entityManager.flush();

        List<CarModel> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(CarModel::getMaker).containsOnly(audi.getMaker(), bmw.getMaker(), mercedes.getMaker());
    }
}
