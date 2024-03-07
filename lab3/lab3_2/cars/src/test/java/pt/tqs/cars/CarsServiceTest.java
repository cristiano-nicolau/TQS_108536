package pt.tqs.cars;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import pt.tqs.cars.CarModel;
import pt.tqs.cars.CarRepository;
import pt.tqs.cars.CarService;


@ExtendWith(MockitoExtension.class)
public class CarsServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setUp() {

        CarModel audi = new CarModel("Audi", "A4");
        audi.setcarId(1L);
        CarModel bmw = new CarModel("BMW", "X1");
        bmw.setcarId(2L);
        CarModel mercedes = new CarModel("Mercedes", "C180");

        when(carRepository.findByCarId(1L)).thenReturn(audi);
        when(carRepository.findByCarId(2L)).thenReturn(bmw);
        when(carRepository.findByCarId(3L)).thenReturn(null);


        List<CarModel> allCars = Arrays.asList(audi, bmw, mercedes);
        when(carRepository.findAll()).thenReturn(allCars);
    }
    
    @Test
    public void whenValidId_thenCarShouldBeFound() {
        CarModel found = carService.getCarDetails(1L);
        assertThat(found.getMaker()).isEqualTo("Audi");

        found = carService.getCarDetails(2L);
        assertThat(found.getMaker()).isEqualTo("BMW");
    }
    
    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        CarModel fromDb = carService.getCarDetails(3L);
        assertThat(fromDb).isNull();

    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        List<CarModel> allCars = carService.getAllCars();
        assertThat(allCars).hasSize(3).extracting(CarModel::getMaker).contains("Audi", "BMW", "Mercedes");
    }
}
