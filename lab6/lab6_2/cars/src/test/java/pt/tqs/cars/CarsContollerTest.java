package pt.tqs.cars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import pt.tqs.cars.CarModel;
import pt.tqs.cars.CarService;
import pt.tqs.cars.CarController;

import pt.tqs.cars.JsonUtils;

@WebMvcTest(CarController.class)
class CarsContollerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CarService service;

	@BeforeEach
	void setUp() {
	}

	@Test
	void whenPostCar_thenCreateCar() throws Exception {
		CarModel audi = new CarModel("Audi", "A4");
		when(service.save(any())).thenReturn(audi);

		mvc.perform(post("/api/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(audi)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.maker", is("Audi")));

		verify(service, times(1)).save(any());
	}

	@Test
	void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
		CarModel audi = new CarModel("Audi", "A4");
		CarModel bmw = new CarModel("BMW", "M3");
		CarModel mercedes = new CarModel("Mercedes", "C220");

		List<CarModel> allCars = Arrays.asList(audi, bmw, mercedes);

		when(service.getAllCars()).thenReturn(allCars);

		mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].maker", is("Audi")))
				.andExpect(jsonPath("$[1].maker", is("BMW"))).andExpect(jsonPath("$[2].maker", is("Mercedes")));

		verify(service, times(1)).getAllCars();
	}

	@Test
	void whenGetCarById_thenReturnJsonArray() throws Exception {
		CarModel audi = new CarModel("Audi", "A4");

		when(service.getCarDetails(anyLong())).thenReturn(audi);

		mvc.perform(get("/api/car/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.maker", is("Audi")));

		verify(service, times(1)).getCarDetails(anyLong());
	}

	@Test
	void whenGetBadCarId_thenReturnJsonArray() throws Exception {
		when(service.getCarDetails(anyLong())).thenReturn(null);

		mvc.perform(get("/api/car/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		verify(service, times(1)).getCarDetails(anyLong());
	}
}
