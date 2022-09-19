package client;

import client.util.*;
import com.request_response.Response;

import java.io.IOException;


public interface IResponseHandler {
     void handle(Response response) throws IOException;
}
