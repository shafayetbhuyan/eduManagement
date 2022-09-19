package client.ui.controller;
import client.RequestSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.request_response.Request;


import common.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Label loginMessageLabel;
    @FXML
    public ImageView profileImageView;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button loginButton;
    @FXML
    private ImageView loginBannerImageView;
    @FXML
    private Button cancelButton;

    private User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File loginBannerPath = new File("resources/images/loginsidebaner-01.jpg");
        Image loginBannerImage = new Image(loginBannerPath.toURI().toString());
        loginBannerImageView.setImage(loginBannerImage);

        File profileImagePath = new File("resources/images/profile.png");
        Image profileImage = new Image(profileImagePath.toURI().toString());
        profileImageView.setImage(profileImage);

    }

    public void loginButtonAction(ActionEvent actionEvent) throws Exception {
        String checkUser = usernameTextField.getText().toString();
        String checkPassword = passwordTextField.getText().toString();

        if (checkUser.isEmpty() || checkPassword.isEmpty()) {
            loginMessageLabel.setText("Please give username & Password");
        } else {

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("userName", checkUser);
            data.put("password", checkPassword);
            Request request = new Request("/login", data);
            RequestSender requestSender = new RequestSender();

            requestSender.sendRequest(request, (response -> {
                if (response.getCode() == 200) {
                   User user = (User) response.getData();
                   if(user.getType() == 0){
                       Parent teacherPane = FXMLLoader.load(getClass().getResource("../Teacher.fxml"));
                        Scene teacherScene= new Scene(teacherPane);

                        Stage teacherStage= new Stage();
                        teacherStage.setScene(teacherScene);
                        client.client.primaryStage.close();
                        teacherStage.show();
                   }
                   else{

                       try {

                       Gson gson= new GsonBuilder().setPrettyPrinting().create();
                       FileWriter fileWriter=new FileWriter("current_user_student.json");
                       gson.toJson(user, fileWriter);
                       fileWriter.flush();
                       fileWriter.close();
                       }catch (Exception e){
                           e.printStackTrace();
                       }

                       Parent studentPane = FXMLLoader.load(getClass().getResource("../Student.fxml"));
                        Scene studentScene= new Scene(studentPane);

                        Stage studentStage= new Stage();
                        studentStage.setScene(studentScene);
                        client.client.primaryStage.close();
                        studentStage.show();
                   }
                } else {
                    loginMessageLabel.setText("Wrong username & Password");
                }
            }));
        }

//        CheckBox CheckBox = new CheckBox();
//        if (usernameTextField.getText().isEmpty() ==false && passwordTextField.getText().isEmpty()==false){
//            loginMessageLabel.setText("You tried to login!");
//        }else {
//            loginMessageLabel.setText("Please enter username and password");
//        }


//        Students students = new Students();
//        Optional<Student> student = students.authenticate(checkUser, checkPassword);
//
//        Teachers teachers = new Teachers();
//        Optional<Teacher> teacher = teachers.authenticate(checkUser, checkPassword);
//
//
//        if (checkUser.isEmpty() || checkPassword.isEmpty()) {
//            loginMessageLabel.setText("Please give username & Password");
//        } else {
//            if (checkBox.isSelected() && teacher.isPresent()){
//                Parent teacherPane = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
//                Scene teacherScene= new Scene(teacherPane);
//
//                Stage teacherStage= new Stage();
//                teacherStage.setScene(teacherScene);
//                Main.primaryStage.close();
//                teacherStage.show();
//            }
//            if(checkBox.isSelected() && !teacher.isPresent()){
//                loginMessageLabel.setText("Wrong username & Password");
//            }
//            if (!checkBox.isSelected() && student.isPresent()){
//                Parent studentPane = FXMLLoader.load(getClass().getResource("Student.fxml"));
//                Scene studentScene= new Scene(studentPane);
//
//                Stage studentStage= new Stage();
//                studentStage.setScene(studentScene);
//                Main.primaryStage.close();
//                studentStage.show();
//            }
//            if (!checkBox.isSelected() && !student.isPresent()){
//                loginMessageLabel.setText("Wrong username & Password");
//            }
//        }
//


    }


        public void cancelButtonAction (ActionEvent actionEvent){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }

    }

