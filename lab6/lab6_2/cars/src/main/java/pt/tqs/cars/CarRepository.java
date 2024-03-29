package pt.tqs.cars;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.tqs.cars.CarModel;

public interface CarRepository extends JpaRepository<CarModel, Long>{
    public CarModel findByCarId(Long carId);
    public List<CarModel> findAll();
}
