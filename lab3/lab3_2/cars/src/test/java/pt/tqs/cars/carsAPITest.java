package pt.tqs.cars;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


import static org.assertj.core.api.Assertions.assertThat;

import pt.tqs.cars.CarModel;
import pt.tqs.cars.CarRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application-test.properties") 
public class carsAPITest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        CarModel audi = new CarModel("Audi", "A4");
        ResponseEntity<CarModel> entity = restTemplate.postForEntity("/api/car", audi, CarModel.class);

        List<CarModel> found = carRepository.findAll();
        assertThat(found).extracting(CarModel::getMaker).containsOnly("Audi");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        createTestCar("Audi", "A4");
        createTestCar("BMW", "X1");
        createTestCar("Mercedes", "C180");

        ResponseEntity<List<CarModel>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarModel>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(CarModel::getMaker).containsExactly("Audi", "BMW", "Mercedes");

    }

    private void createTestCar(String maker, String model) {
        CarModel car = new CarModel(maker, model);
        carRepository.saveAndFlush(car);
    }

    @Test
    public void testGetCarByIDnull(){
        ResponseEntity<CarModel> response = restTemplate.getForEntity("/api/cars/2", CarModel.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getCarById() {
        CarModel audi = new CarModel("Audi", "A4");
        carRepository.saveAndFlush(audi);

        ResponseEntity<CarModel> response = restTemplate.getForEntity("/api/car/" + audi.getcarId(), CarModel.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMaker()).isEqualTo(audi.getMaker());
    }
    
}
