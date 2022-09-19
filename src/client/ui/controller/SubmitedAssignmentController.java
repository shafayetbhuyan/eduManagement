package client.ui.controller;

import client.RequestSender;
import com.request_response.Request;
import common.entity.Assignment;
import common.entity.SubmitedAssignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SubmitedAssignmentController implements Initializable {

    @FXML
    public TableView<SubmitedAssignment> submitedAssignmentListTableView;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> studentNameTabCol;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> studentIDTabCol;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> assignmentNoTabCol;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> assignmentNameTabCol;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> publishDateTabCol;
    @FXML
    public TableColumn<SubmitedAssignment, Integer> submitDateTabCol;

    public Label messageLabel;

    private String studentId;
    private String assignmentNo;







    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Selection Model
        submitedAssignmentListTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Request request= new Request("/teacher/sunmited_assignments/");
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                if (response.getCode()==200){

                    ArrayList<SubmitedAssignment> submitedAssignments = (ArrayList<SubmitedAssignment>) response.getData();
                    ObservableList<SubmitedAssignment> list = FXCollections.observableArrayList(submitedAssignments);

                    studentNameTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("studentName"));
                    studentIDTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("studentID"));
                    assignmentNoTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("assignmentNo"));
                    assignmentNameTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("assignmentName"));
                    publishDateTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("publishDate"));
                    submitDateTabCol.setCellValueFactory(new PropertyValueFactory<SubmitedAssignment, Integer>("submitDate"));

                    submitedAssignmentListTableView.setItems(list);

                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void viewSelectedAssignmentAction(ActionEvent actionEvent) {



        if (studentId != null && assignmentNo != null){
            Map<String, Object> data= new LinkedHashMap<>();
            data.put("studentId", studentId);
            data.put("assignmentNo", assignmentNo);

            Request request= new Request("/teacher/submited_assignment_list/view_assignment/student", data);

            RequestSender requestSender= new RequestSender();

            try {
                requestSender.sendRequest(request, response -> {

                    SubmitedAssignment submitedAssignment = (SubmitedAssignment) response.getData();
                    String studentName = submitedAssignment.getStudentName();
                    String studentID = submitedAssignment.getStudentID();
                    String assignmentNo = submitedAssignment.getAssignmentNo();
                    String assignmentName = submitedAssignment.getAssignmentName();
                    String publishDate = submitedAssignment.getPublishDate();
                    String submitDate = submitedAssignment.getSubmitDate();
                    String assignmentFilePath = submitedAssignment.getAssignmentFilePath();

                    FXMLLoader loader= new FXMLLoader(getClass().getResource("../ReviewStudentsSubmitedAssignment.fxml"));
                    Parent teacherPane = loader.load();

                    ReviewStudentsSubmitedAssignmentController controller = loader.getController();
                    controller.showSelectedAssignment(studentName, studentID, assignmentNo, assignmentName, publishDate, submitDate, assignmentFilePath);

                    Stage assignmentStage= new Stage();
                    assignmentStage.setScene(new Scene(teacherPane));
                    assignmentStage.setTitle("Assignment");
                    assignmentStage.show();

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            messageLabel.setText("Please Select Assignment");
        }



    }

    public void selectAssignmentRowAction(MouseEvent mouseEvent) {
        SubmitedAssignment selectedAssignment = submitedAssignmentListTableView.getSelectionModel().getSelectedItem();
        String assignmentName = selectedAssignment.getAssignmentName();
        String studentName = selectedAssignment.getStudentName();

        this.studentId = selectedAssignment.getStudentID();
        this.assignmentNo = selectedAssignment.getAssignmentNo();

        messageLabel.setText("Student: "+studentName+ "  |  Assignment: "+ assignmentName);
    }
}
