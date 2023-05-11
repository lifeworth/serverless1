package helloworld;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void successfulResponse() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");


        App app = new App();
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
        input.setBody("hello world");
        input.setHeaders(headers);
        input.setHttpMethod("POST");
        input.setPath("/uuid");

        APIGatewayProxyResponseEvent result = app.handleRequest(input, null);
        assertEquals(200, result.getStatusCode().intValue());
        assertEquals("application/json", result.getHeaders().get("Content-Type"));
        String content = result.getBody();
        assertNotNull(content);
        System.out.println(content);
    }
}
