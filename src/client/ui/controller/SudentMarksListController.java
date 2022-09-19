package client.ui.controller;

import client.RequestSender;
import com.request_response.Request;
import common.entity.Marks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SudentMarksListController  implements Initializable {

    @FXML
    public TableView<Marks> tableMarks;
    public TableColumn<Marks, String> tabColStudentID;
    public TableColumn<Marks, String> tabColStudentName;
    public TableColumn<Marks, String> tabColAssignmentID;
    public TableColumn<Marks, String> tabColAssignmentName;
    public TableColumn<Marks, Integer> tabColMarks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Request request= new Request("/teacher/students/marks/");
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                List<Marks> marksList = (List<Marks>) response.getData();


                ObservableList<Marks> marks= FXCollections.observableArrayList(marksList);

                tabColStudentID.setCellValueFactory(new PropertyValueFactory<Marks, String>("studentID"));
                tabColStudentName.setCellValueFactory(new PropertyValueFactory<Marks, String>("studentName"));
                tabColAssignmentID.setCellValueFactory(new PropertyValueFactory<Marks, String>("assignmentID"));
                tabColAssignmentName.setCellValueFactory(new PropertyValueFactory<Marks, String>("assignmentName"));
                tabColMarks.setCellValueFactory(new PropertyValueFactory<Marks, Integer>("mark"));

                tableMarks.setItems(marks);


            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
