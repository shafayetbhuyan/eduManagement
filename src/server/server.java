package server;

import server.Controller.IController;
import server.Controller.StudentController;
import server.Controller.TeacherController;
import server.Controller.UserController;
import server.util.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {

    public static void main(String[] args) throws Exception {

        RequestHandler requestHandler = new RequestHandler();

        List<IController> controllerList = new ArrayList<>();

        controllerList.add(new StudentController());
        controllerList.add(new UserController());
        controllerList.add(new TeacherController());
        // more Controllers to be added.


        controllerList.forEach(controller -> {
            controller.addMappings();
        });




        ServerSocket serverSocket = new ServerSocket(ServerConfig.port);
        System.out.println("server has been started at port: " + ServerConfig.port);
        while (true){
            Socket socket = serverSocket.accept();
            SocketUtil socketUtil = new SocketUtil(socket);
            new ReadThreadServer(socketUtil);
        }
    }
}
