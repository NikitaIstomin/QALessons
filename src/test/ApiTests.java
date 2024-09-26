package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.ApiRequestPage;
import java.util.Map;

public class ApiTests {

    private ApiRequestPage apiRequestPage;

    @BeforeClass
    public void setUp() {
        apiRequestPage = new ApiRequestPage();  // Инициализация Page Object для запросов API
    }

    @Test
    public void getTest() {
        // Параметры GET-запроса
        Map<String, String> queryParams = Map.of(
                "foo1", "bar1",
                "foo2", "bar2"
        );

        Response response = apiRequestPage.sendGetRequest(queryParams);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");

        Assert.assertEquals(response.jsonPath().getString("args.foo1"), "bar1", "Неверное значение foo1");
        Assert.assertEquals(response.jsonPath().getString("args.foo2"), "bar2", "Неверное значение foo2");
    }

    @Test
    public void postRawTextTest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = apiRequestPage.sendPostRawTextRequest(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");
        Assert.assertEquals(response.jsonPath().getString("data"), requestBody, "Поле 'data' содержит неправильный текст.");
    }

    @Test
    public void postFormDataTest() {
        // Параметры для form-data
        Map<String, String> formParams = Map.of(
                "foo1", "bar1",
                "foo2", "bar2"
        );

        Response response = apiRequestPage.sendPostFormDataRequest(formParams);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");
        Assert.assertEquals(response.jsonPath().getString("form.foo1"), "bar1", "Поле 'foo1' не содержит значение 'bar1'.");
        Assert.assertEquals(response.jsonPath().getString("form.foo2"), "bar2", "Поле 'foo2' не содержит значение 'bar2'.");
    }

    @Test
    public void putRequestTest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = apiRequestPage.sendPutRequest(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");
        Assert.assertEquals(response.jsonPath().getString("data"), requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = apiRequestPage.sendPatchRequest(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");
        Assert.assertEquals(response.jsonPath().getString("data"), requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }

    @Test
    public void deleteRequestTest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = apiRequestPage.sendDeleteRequest(requestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "Статус-код не равен 200");
        Assert.assertEquals(response.jsonPath().getString("data"), requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }
}