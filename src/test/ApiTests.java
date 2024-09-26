package test;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class ApiTests {

    @Test
    @DisplayName("Запрос GET")
    public void getTest() {
        RestAssured.baseURI = "https://postman-echo.com/get";

        Response response = RestAssured.given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get();

        response.then().statusCode(200);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode, "статус-код не равен 200");

        response.then()
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));

        Assert.assertTrue(response.asString().contains("foo1"));
        Assert.assertTrue(response.asString().contains("bar1"));
        Assert.assertTrue(response.asString().contains("foo2"));
        Assert.assertTrue(response.asString().contains("bar2"));
    }

    @Test
    @DisplayName("Запрос POST")
    public void postRawTextTest() {
        String baseUrl = "https://postman-echo.com/post";

        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .post(baseUrl);

        int statusCode = response.getStatusCode();
        String receivedText = response.jsonPath().getString("data");
        String responseHost = response.jsonPath().getString("headers.host");
        String url = response.jsonPath().getString("url");
        Map<String, Object> args = response.jsonPath().getMap("args");
        Map<String, String> headers = response.jsonPath().getMap("headers");

        Assert.assertEquals(200, statusCode, "статус-код не равен 200.");
        Assert.assertEquals(requestBody, receivedText, "Поле 'data' содержит неправильный текст.");
        Assert.assertEquals(baseUrl, url, "URL не совпадает с базовым URL запроса.");
        Assert.assertTrue(args.isEmpty(), "поле args не пустое.");
        Assert.assertEquals("postman-echo.com", responseHost, "заголовок 'host' содержит неправильное значение.");
        Assert.assertTrue(headers.containsKey("content-length"), "Поле content-length отсутствует в заголовке.");
    }

    @Test
    @DisplayName("Проверка POST-запроса с form data")
    public void postFormDataTest() {
        // Устанавливаем базовый URL
        String baseUrl = "https://postman-echo.com/post";

        Map<String, String> formParams = Map.of(
                "foo1", "bar1",
                "foo2", "bar2"
        );

        Response response = RestAssured.given()
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .post(baseUrl);

        int statusCode = response.getStatusCode();
        String url = response.jsonPath().getString("url");
        Map<String, String> formData = response.jsonPath().getMap("form");
        Map<String, String> headers = response.jsonPath().getMap("headers");

        Assert.assertEquals(statusCode, 200, "Статус-код не равен 200.");

        Assert.assertEquals(formData.get("foo1"), "bar1", "Поле 'foo1' не содержит значение 'bar1'.");
        Assert.assertEquals(formData.get("foo2"), "bar2", "Поле 'foo2' не содержит значение 'bar2'.");

        Assert.assertEquals(url, baseUrl, "URL в ответе не совпадает с базовым URL запроса.");

        String hostHeader = headers.get("host");
        Assert.assertEquals(hostHeader, "postman-echo.com", "Заголовок 'host' имеет неправильное значение.");

        String contentType = headers.get("content-type");
        Assert.assertEquals(contentType, "application/x-www-form-urlencoded", "Заголовок 'content-type' имеет неправильное значение.");

        Assert.assertTrue(response.asString().contains("foo1"), "Ответ не содержит параметр 'foo1'.");
        Assert.assertTrue(response.asString().contains("foo2"), "Ответ не содержит параметр 'foo2'.");
        Assert.assertTrue(response.asString().contains("bar1"), "Ответ не содержит значение 'bar1'.");
        Assert.assertTrue(response.asString().contains("bar2"), "Ответ не содержит значение 'bar2'.");
    }

    @Test
    @DisplayName("запрос PUT")
    public void PutRequestTest() {
        // URL для PUT-запроса
        String baseUrl = "https://postman-echo.com/put";

        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .put(baseUrl);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Статус-код не равен 200");

        String receivedText = response.jsonPath().getString("data");
        Assert.assertEquals(receivedText, requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }

    @Test
    @DisplayName("запрос PATCH")
    public void testPatchRequest() {
        String baseUrl = "https://postman-echo.com/patch";

        String requestBody = "This is expected to be sent back as part of response body.";

        // Выполнение PATCH-запроса с заголовком и телом
        Response response = RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .patch(baseUrl);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Статус-код не равен 200");

        String receivedText = response.jsonPath().getString("data");
        Assert.assertEquals(receivedText, requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }

    @Test
    @DisplayName("запрос DELETE")
    public void deleteRequestTest() {
        String baseUrl = "https://postman-echo.com/delete";

        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .delete(baseUrl);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Статус-код не равен 200");

        String receivedText = response.jsonPath().getString("data");
        Assert.assertEquals(receivedText, requestBody, "Текст в поле 'data' не совпадает с ожидаемым значением");
    }
}


