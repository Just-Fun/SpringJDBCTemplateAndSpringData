package ua.com.juja.sqlcmd.integraion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.service.Service;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: oleksandr.baglai
 * Date: 11/18/12
 * Time: 4:47 PM
 */
public class MockIntegrationTest {
    private WebDriver driver;
    private SpringMockerJettyRunner runner;
    private Service service;

    @Before
    public void startServer() throws Exception {
        runner = new SpringMockerJettyRunner("src/main/webapp", "/sqlcmd");
        driver = new HtmlUnitDriver(true);
    }

    @After
    public void stop() throws Exception {
        runner.stop();
    }

    @Test
    public void test() throws Exception {
        // given
        runner.mockBean("service");
        runner.start();
        service = runner.getBean("service");

        when(service.commandsList()).thenReturn(Arrays.asList("help", "list"));

        DatabaseManager manager = mock(DatabaseManager.class);
        when(service.connect(anyString(), anyString(), anyString()))
                .thenReturn(manager);

        when(service.tables(manager))
                .thenReturn(new LinkedHashSet<String>(Arrays.asList("table1", "table2")));

        // when
        driver.get(runner.getUrl());

        driver.findElement(By.id("database")).sendKeys("databaseName");
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("connect")).click();

        // then
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        List<String> names = new LinkedList<>();
        List<String> urls = new LinkedList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
            urls.add(element.getAttribute("href"));
        }
        assertEquals("[help, list]", names.toString());
        String base = "http://localhost:" + runner.getPort();
        assertEquals("[/sqlcmd/help, " +
                "/sqlcmd/list]", urls.toString()
                .replaceAll(base, ""));

        // when
        driver.get(base + "/sqlcmd/list");

        // then
        List<WebElement> elements2 = driver.findElements(By.tagName("a"));
        List<String> names2 = new LinkedList<>();
        List<String> urls2 = new LinkedList<>();
        for (WebElement element : elements2) {
            names2.add(element.getText());
            urls2.add(element.getAttribute("href"));
        }
        assertEquals("[table1, table2]", names2.toString());
        assertEquals("[/sqlcmd/find?table=table1, " +
                "/sqlcmd/find?table=table2]", urls2.toString()
                .replaceAll(base, ""));
    }


}