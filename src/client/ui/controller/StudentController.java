package client.ui.controller;

import common.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    public BorderPane bodyPane;
    public Label checkLevel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void AssignmentListAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../StudentAssignmentList.fxml"));
        bodyPane.setCenter(pane);
    }

    public void getUserProfile(ActionEvent actionEvent) {

    }

    public void studentAssignmentMarksListAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../StudentsResults.fxml"));
        bodyPane.setCenter(pane);
    }
}
