package client.ui.controller;

import client.RequestSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.request_response.Request;
import common.entity.Assignment;
import common.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentAssignmentListController implements Initializable {
    @FXML
    public ListView<String> assignmentsListView;
    @FXML
    public Label selectedAsTitleLable;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        assignmentsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Request request= new Request("/student/assignment_list");
        RequestSender requestSender= new RequestSender();

        try {

            requestSender.sendRequest(request, response -> {

                ArrayList<String> asList = (ArrayList<String>) response.getData();
                    ObservableList respAssignments= FXCollections.observableArrayList(asList);
                    assignmentsListView.setItems(respAssignments);


            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void selectedAssignmentTitleAction(MouseEvent mouseEvent) {
        String selectedItem = assignmentsListView.getSelectionModel().getSelectedItem().toString();
        selectedAsTitleLable.setText(selectedItem);
    }

    public void viewAssignmentAction(ActionEvent actionEvent) throws Exception{

        String selectedItem = assignmentsListView.getSelectionModel().getSelectedItem().toString();


        if (selectedItem != null){
            Map<String, Object> data= new LinkedHashMap<>();
            data.put("asTitle", selectedItem);

            Request request= new Request("/student/assignment_list/view_assignment/", data);

            RequestSender requestSender= new RequestSender();

            requestSender.sendRequest(request, response -> {


                Assignment selectedAssignment = (Assignment) response.getData();
                String assignmentId = selectedAssignment.getAssignmentId();
                String assignmentName = selectedAssignment.getAssignmentName();
                LocalDate publishedDate= selectedAssignment.getPublishedDate();
                LocalDate submitdDate= selectedAssignment.getSubmitDate();
                String assignmentFilePath = selectedAssignment.getAssignmentFilePath();


                FXMLLoader loader= new FXMLLoader(getClass().getResource("../StudentAssignmentUpload.fxml"));
                Parent studentAssignmentPane = loader.load();

                StudentAssignmentUploadController assignmentController = loader.getController();

                assignmentController.showSelectedAssignment(assignmentId, assignmentName, publishedDate, submitdDate, assignmentFilePath);




                Stage assignmentStage= new Stage();
                assignmentStage.setScene(new Scene(studentAssignmentPane));
                assignmentStage.setTitle("Assignment Upload");
                assignmentStage.show();

            });
        }else {
            selectedAsTitleLable.setText("Please Select Assignment");
        }


    }
}
