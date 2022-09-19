package client;

import client.util.*;
import com.request_response.Request;

import java.net.Socket;

public class RequestSender {

    SocketUtil socketUtil;
    Request request;
    IResponseHandler responseHandler;


    public void sendRequest(Request request, IResponseHandler responseHandler) throws Exception {
        this.request = request;
        this.responseHandler = responseHandler;
        if(this.request == null){
            throw new Exception("No request to send");
        }else{
            Socket socket = new Socket(ClientConfig.host, ClientConfig.port);
            this.socketUtil = new SocketUtil(socket);
            new RequestSenderHelper(this.socketUtil, this.request, this.responseHandler);
        }
    }

}