package pt.tqs.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.tqs.cars.CarModel;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarModel save(CarModel car) {
        return carRepository.save(car);
    }

    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    public CarModel getCarDetails(Long id) {
        return carRepository.findByCarId(id);
    }


}
