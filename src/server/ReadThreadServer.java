package server;

import com.request_response.Request;
import com.request_response.Response;
import server.util.*;
import java.io.IOException;


public class ReadThreadServer implements Runnable {

    Thread t;
    SocketUtil socketUtil;

    public ReadThreadServer(SocketUtil socketUtil){
        this.socketUtil = socketUtil;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run(){
        while (! this.socketUtil.isClosed()){

            try {
                Request request = (Request)this.socketUtil.readSocket();
                Response response = RequestHandler.handle(request);
                this.socketUtil.writeSocket(response);
            } catch (Exception e) {
//                e.printStackTrace();
                try {
                    this.socketUtil.closeSocket();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
