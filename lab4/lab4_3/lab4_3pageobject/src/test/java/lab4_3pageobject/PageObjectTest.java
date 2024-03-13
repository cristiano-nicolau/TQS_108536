package lab4_3pageobject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class PageObjectTest {
    private WebDriver driver;


    @FindBy(name="fromPort")
    private WebElement fromPort;
  
    @FindBy(xpath = "//option[. = 'Philadelphia']")
    private WebElement fromPortOption;
  
    @FindBy(name="toPort")
    private WebElement toPort;
  
    @FindBy(xpath = "//option[. = 'Rome']")
    private WebElement toPortOption;
  
    @FindBy(css=".btn-primary")
    private WebElement btn1;
  
    @FindBy(css="tr:nth-child(3) .btn")
    private WebElement btn2;
  
    @FindBy(name="inputName")
    private WebElement inputName;
  
    @FindBy(name="address")
    private WebElement address;
  
    @FindBy(name="city")
    private WebElement city;
  
    @FindBy(name="state")
    private WebElement state;
  
    @FindBy(name="zipCode")
    private WebElement zipCode;
  
    @FindBy(name="cardType")
    private WebElement cardType;
  
    @FindBy(css="option:nth-child(2)")
    private WebElement btn3;
  
    @FindBy(xpath = "//option[. = 'American Express']")
    private WebElement cardTypeOption;
  
    @FindBy(name="creditCardNumber")
    private WebElement creditCardNumber;
  
    @FindBy(name="nameOnCard")
    private WebElement nameOnCard;
  
    @FindBy(css=".checkbox")
    private WebElement btn4;
  
    @BeforeEach
    public void setup() {
       WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();  
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bookFlightTest() {
        driver.get("https://blazedemo.com/");
        PageFactory.initElements(driver, this);
        
        fromPortOption.click();
        toPortOption.click();
        btn1.click();
        btn2.click();
        inputName.sendKeys("Cristianinho dos Santos");
        address.sendKeys("vindo diretamente da madeira");
        city.sendKeys("madeira");
        state.sendKeys("açores");
        zipCode.sendKeys("1234");
        cardTypeOption.click();
        btn3.click();
        creditCardNumber.sendKeys("123456");
        nameOnCard.sendKeys("RonaldinhoJunior");
        btn4.click();
        btn1.click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
      }
}

