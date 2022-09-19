package server;

import com.request_response.Request;
import com.request_response.Response;
import server.util.*;

import java.io.IOException;

public interface IRequestHandler {

    Response handle(Request request) throws IOException;
}
