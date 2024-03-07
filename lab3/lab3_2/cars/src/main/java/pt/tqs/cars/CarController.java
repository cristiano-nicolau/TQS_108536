package pt.tqs.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.tqs.cars.CarModel;
import pt.tqs.cars.CarService;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseEntity<CarModel> createCar(@RequestBody CarModel car) {
        return new ResponseEntity<>(carService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<Iterable<CarModel>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarModel> getCar(@PathVariable Long id) {
        CarModel car = carService.getCarDetails(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
