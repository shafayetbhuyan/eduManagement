package client.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {


    public BorderPane bodyPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void viewStudentAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../ViewStudents.fxml"));
        bodyPane.setCenter(pane);
    }

    public void createAssignmentAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../CreateAssignment.fxml"));
        bodyPane.setCenter(pane);
    }

    public void AssignmentListAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../AssignmentList.fxml"));
        bodyPane.setCenter(pane);
    }

    public void submitedAssignmentAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../SubmitedAssignment.fxml"));
        bodyPane.setCenter(pane);
    }

    public void studentMarksAction(ActionEvent actionEvent) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("../SudentMarksList.fxml"));
        bodyPane.setCenter(pane);
    }
}
