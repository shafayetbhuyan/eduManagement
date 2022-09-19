package server;

import com.request_response.Request;
import com.request_response.Response;

import java.io.IOException;
import java.util.LinkedHashMap;

import java.util.Map;

public class RequestHandler {

    private static Map<String, IRequestHandler> requestHandlerMap;

    public RequestHandler(){
        if(requestHandlerMap == null) {
            requestHandlerMap = new LinkedHashMap();
        }
    }
    public static void addMappin(String url, IRequestHandler responseHandler){
        requestHandlerMap.put(url, responseHandler);
    }

    public static Response handle(Request request) throws IOException {
        return requestHandlerMap.get(request.getUrl()).handle(request);
    }
}
