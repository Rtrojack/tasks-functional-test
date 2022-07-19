import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    @Test
    public void deveInserirTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = getWebDriver();
        try {
            //clicar no botao
            driver.findElement(By.id("addTodo")).click();

            //preencher formulario
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            driver.findElement(By.id("duedate")).sendKeys("10/10/2099");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validaçoes
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Sucess!", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveInserirTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = getWebDriver();
        try {
            //clicar no botao
            driver.findElement(By.id("addTodo")).click();

            //preencher formulario
            //driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            driver.findElement(By.id("duedate")).sendKeys("10/10/2020");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validaçoes
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveInserirTarefaSemData() throws MalformedURLException {
        WebDriver driver = getWebDriver();
        try {
            //clicar no botao
            driver.findElement(By.id("addTodo")).click();

            //preencher formulario
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            //driver.findElement(By.id("duedate")).sendKeys("10/10/2020");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validaçoes
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveInserirTarefaComDataAnterior() throws MalformedURLException {
        WebDriver driver = getWebDriver();
        try {
            //clicar no botao
            driver.findElement(By.id("addTodo")).click();

            //preencher formulario
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            driver.findElement(By.id("duedate")).sendKeys("10/10/2020");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validaçoes
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", mensagem);
        } finally {
            driver.quit();
        }
    }

    private WebDriver getWebDriver() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        driver.navigate().to("http://192.168.20.72:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
