package client;

import client.util.*;
import com.request_response.Request;
import com.request_response.Response;
import javafx.application.Platform;

import java.io.IOException;

public class RequestSenderHelper implements Runnable {

    Thread t;
    SocketUtil socketUtil;
    Request request;
    IResponseHandler responseHandler;


    public RequestSenderHelper(SocketUtil socketUtil,  Request request, IResponseHandler responseHandler){
        this.socketUtil = socketUtil;
        this.request = request;
        this.responseHandler = responseHandler;
        Platform.runLater(this);
    }
    @Override
    public void run(){
        try {
            this.socketUtil.writeSocket(request);
        } catch (Exception e) {
            try {
                this.socketUtil.closeSocket();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        Response response = null;

        while (response == null) {
            try {
                response = (Response)this.socketUtil.readSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(this.responseHandler != null){
            try {
                this.responseHandler.handle(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.socketUtil.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}