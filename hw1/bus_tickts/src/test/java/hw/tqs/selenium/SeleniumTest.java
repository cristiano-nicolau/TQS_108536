package hw.tqs.selenium;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;


public class SeleniumTest {
    private WebDriver driver;
    private HomePage homePage;
    private ReservationPage reservationPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        homePage = new HomePage(driver);
        reservationPage = new ReservationPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Mark a trip and buy a ticket flow")
    public void testFlightReservation() {
        // Navega para a página inicial
        homePage.goTo();
        sleepOneSecond();

        // Seleciona as opções de viagem
        homePage.selectDeparture("Coimbra");
        sleepOneSecond();
        homePage.selectArrival("Porto");
        sleepOneSecond();
        homePage.selectDepartureDate("11-04-2024");
        sleepOneSecond();
        homePage.clickOneWayButton();
        sleepOneSecond();

        // Avança para a próxima etapa
        homePage.clickNextButton();
        sleepOneSecond();

        // Seleciona um assento e confirma a reserva
        reservationPage.selectSeat(5);
        sleepOneSecond();
        reservationPage.clickConfirmButton();
        sleepOneSecond();

        // Preenche os detalhes do passageiro e avança para a página de tickets
        reservationPage.fillPassengerDetails("John Doe", "abc@example.com", "123456789", "123456789", "123 Main St",
                "City", "12345", "1234567890123456");
        sleepOneSecond();

        assertThat(reservationPage.getPassengerName()).isEqualTo("John Doe");
        assertThat(reservationPage.getPassengerEmail()).isEqualTo("abc@example.com");
        assertThat(reservationPage.getPassengerPhone()).isEqualTo("123456789");
        assertThat(reservationPage.getPassengerNif()).isEqualTo("123456789");
        assertThat(reservationPage.getPassengerAddress()).isEqualTo("123 Main St");
        assertThat(reservationPage.getPassengerCity()).isEqualTo("City");
        assertThat(reservationPage.getPassengerZip()).isEqualTo("12345");
        assertThat(reservationPage.getPassengerCardNumber()).isEqualTo("1234567890123456");

        reservationPage.clickYourTicketsButton();
        sleepOneSecond();
    }

    @Test
    @DisplayName("Test origin, destination and date selection")
    public void testSelectOptions() {
        // Navega para a página inicial
        homePage.goTo();
        sleepOneSecond();

        // Seleciona as opções de viagem
        homePage.selectDeparture("Aveiro");
        sleepOneSecond();
        homePage.selectArrival("Braga");
        sleepOneSecond();
        homePage.selectDepartureDate("11-04-2024");
      
        assertThat(homePage.getSelectedDeparture()).isEqualTo("Aveiro");
        assertThat(homePage.getSelectedArrival()).isEqualTo("Braga");
        assertThat(homePage.getSelectedDepartureDate()).isEqualTo("2024-04-11");
    }


    @Test
    @DisplayName("See a ticket flow")
    public void testViewTicket() {
        // Navega para a página inicial
        homePage.goTo();
        sleepOneSecond();

        // Clica no botão de finalizar
        reservationPage.clickFinalizeButton();
        sleepOneSecond();

        // Clica na primeira div renderizada
        reservationPage.clickFirstRenderedDiv();
        sleepOneSecond();
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000); // Aguarda 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
