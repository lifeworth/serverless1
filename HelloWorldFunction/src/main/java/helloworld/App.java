package helloworld;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 * @author zhiyuandu
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        APIGatewayProxyResponseEvent responseEvent = buildResponse();
        //解析请求
        CommonResult req = parseRequest(input, context);

        try {
            //生成json结果
            String res = generateJsonResponse(req);
            return responseEvent.withStatusCode(200).withBody(res);
        } catch (Exception e) {
            return responseEvent.withStatusCode(500).withBody(e.getMessage());
        }

    }

    private CommonResult parseRequest(APIGatewayProxyRequestEvent input, Context context) {
        String body = input.getBody();
        String path = input.getPath();

        if ("/uuid".equalsIgnoreCase(path)) {
            UUID uuid = UUID.fastUUID();
            return CommonResult.SUCCESS(uuid.toString());
        }

        if ("/test".equalsIgnoreCase(path)) {
            return CommonResult.SUCCESS(input);
        }

        return CommonResult.SUCCESS();
    }

    /**
     * 构建Response对象
     * @return
     */
    private APIGatewayProxyResponseEvent buildResponse() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return new APIGatewayProxyResponseEvent().withHeaders(headers);
    }

    /**
     * 根据返回值 生成响应的json
     */
    private String generateJsonResponse(CommonResult result) {
        return JSONUtil.parse(result).toJSONString(2);
    }

}
