import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientTest {
    @Test
    public  void getTest() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/emojis"))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("articulated_lorry")) {
                System.out.println("Test passed. Key is found in the answer");
            } else {
                System.out.println("Test failed. Key is not found in the answer");
            }

            driver.get("https://api.github.com/emojis");
            String pageSource = driver.getPageSource();

            if (pageSource.contains("articulated_lorry")) {
                System.out.println("Test passed. Key is found on page");
            } else {
                System.out.println("Test failed. Key is not found on page");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }


}
