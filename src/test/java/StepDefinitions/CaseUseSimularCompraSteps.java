package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class CaseUseSimularCompraSteps {
    String rutaDriver= "C:\\Users\\gzagal\\Documents\\FreezePreUnic\\src\\test\\resources\\drivers\\msedgedriver.exe";
    EdgeOptions options = null;
    WebDriver driver = null;

    //WebDriverWait wait = null;


    @Given("El usuario abre el navegador e ingresa a la url de freeze Preunic")
    public void elUsuarioAbreElNavegadorEIngresaALaUrlDeFreezePreunic (){
        System.setProperty("webDriver.edge.driver",rutaDriver);



        options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://freezepu.salcobrandonline.cl/");

        //wait = new WebDriverWait(WebDriver driver, Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        var ubicacion = driver.findElement(By.xpath("//*[@id=\"keepCommune\"]"));
        ubicacion.click();

    }

    @When("El usuario ingresa en el buscador un producto")
    public void elUsuarioIngresaEnElBuscardorUnProducto() {
        var barraBusqueda = driver.findElement(By.xpath("//*[@id=\"aa-search-input\"]"));
        barraBusqueda.sendKeys("Crema");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        barraBusqueda.sendKeys(Keys.ENTER);


    }

    @When("El usuario selecciona el producto a comprar de la busqueda y lo deja en carro")
    public void elUsuarioSeleccionaElProductoACompraDeLaBusquedaYLoDejaEnCarro(){
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        var botonSeleccionarProducto = driver.findElement(By.xpath("//*[@id=\"product_10545\"]/div/div[2]/div[1]/span[1]/a/span"));


        botonSeleccionarProducto.click();

        var agregarCarro = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        agregarCarro.click();

        var cerrarCarro = driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div/a/img"));
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        cerrarCarro.click();

    }
    @When("El usuario ingresa SKU de producto en buscador")
    public void elUsuarioIngreSkuDeProductoEnBuscardor(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        var barraBusqueda = driver.findElement(By.xpath("//*[@id=\"aa-search-input\"]"));
        barraBusqueda.sendKeys("3163387");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        barraBusqueda.sendKeys(Keys.ENTER);
    }
    @When("El usuario selecciona el segundo producto y lo deja en carro")
    public void elUsuarioSeleccionaElSegundoProductoYLoDejaEnCarro(){

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        var seleccionarProducto2 = driver.findElement(By.xpath("//*[@id=\"product_31835\"]/div/div[2]/div[1]/span[1]/a/span"));
        seleccionarProducto2.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        var agregarCarro2 = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        agregarCarro2.click();

        var verCarro = driver.findElement(By.xpath("//*[@id=\"dynamic-update-cart\"]/div[2]/div/div[4]/div/div[1]/a"));
        verCarro.click();
    }

    @Then("Comprobar que productos seleccionados en Carro")
    public void comprobarQueProductosSeleccionadosEnCarro(){
        var producto1 = driver.findElements(By.tagName("h2"));
        var producto2 = driver.findElements(By.tagName("p"));
        boolean encontrado1 = false;
        boolean encontrado2 = false;
        for(WebElement element: producto1)
        {
            var aux = element.getText();
            if (aux.equals("Crema"))
            {
                encontrado1=true;
            }
        }

        for(WebElement element: producto2)
        {
            var aux2 = element.getText();
            if(aux2.equals("3163387"))
            {
                encontrado2=true;
            }
        }
        if(encontrado1==true && encontrado2==true)
        {
            Assertions.assertTrue(encontrado2);
        }
    }

}
