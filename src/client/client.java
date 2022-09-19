package client;


import client.ui.controller.LoginController;
import com.request_response.Request;
import common.entity.Check;
import common.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class client extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ui/Login.fxml"));

        primaryStage.setTitle("Login - Assignment Management System");

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
