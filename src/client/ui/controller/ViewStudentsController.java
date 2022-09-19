package client.ui.controller;

import client.RequestSender;
import com.request_response.Request;
import common.entity.User;
import common.entity.Users;
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

public class ViewStudentsController implements Initializable {


    @FXML
    public TableView<User> tableAllStudent;
    @FXML
    public TableColumn<User, Integer> tableColStudentID;
    @FXML
    public TableColumn<User, String> tableColStudentName;
    @FXML
    public TableColumn<User, String> tableColStudentDepertment;
    @FXML
    public TableColumn<User, String> tableColStudentDetails;




    @Override
    public void initialize(URL location, ResourceBundle resources){

        Request request= new Request("/teacher/students");
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                    List<User> respStudent = (List<User>) response.getData();


                    ObservableList<User> students= FXCollections.observableArrayList(respStudent);

                    tableColStudentID.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
                    tableColStudentName.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
                    tableColStudentDepertment.setCellValueFactory(new PropertyValueFactory<User, String>("department"));
                    tableColStudentDetails.setCellValueFactory(new PropertyValueFactory<User, String>("details"));

                    tableAllStudent.setItems(students);


            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
