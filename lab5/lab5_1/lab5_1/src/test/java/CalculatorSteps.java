package pt.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {

    private Calculator calc;

    @Given("^a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I add floats {float} and {float}")
    public void addFloats(float arg1, float arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} to {int}")
    public void multiply(int arg1, int arg2){
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} to {int}")
    public void divide(int arg1, int arg2){
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @When("I square {int}")
    public void square(int arg1){
        calc.push(arg1);
        calc.push(arg1);
        calc.push("*");
    }

    @Then("the result is {int}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        assertEquals(expected, value);
    }

}
