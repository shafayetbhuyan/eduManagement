package client.ui.controller;

import client.RequestSender;
import client.util.ClientConfig;
import client.util.FileUtilClient;
import com.request_response.Request;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReviewStudentsSubmitedAssignmentController implements Initializable {


    public Label studentNameLabel;
    public Label studentIDLabel;
    public Label assignmentNoLabel;
    public Label assignmentTitleLabel;
    public Label publishedDateLabel;
    public Label submitDateLabel;
    public Label messageLabel;
    public TextField assignMarkTextField;

    private String assignmentFilePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void showSelectedAssignment(String studentName, String studentID ,String assignmentId, String assignmentName, String publishedDate, String submitdDate, String assignmentFilePath){
        studentNameLabel.setText(studentName);
        studentIDLabel.setText(studentID);
        assignmentNoLabel.setText(assignmentId);
        assignmentTitleLabel.setText(assignmentName);
        publishedDateLabel.setText(publishedDate);
        submitDateLabel.setText(submitdDate);
        this.assignmentFilePath = assignmentFilePath;
    }




    public void downloadSubmitedAssignmentFileAction(ActionEvent actionEvent) {

        Map<String, Object> data= new LinkedHashMap<>();
        data.put("filePath", assignmentFilePath);

        Request request= new Request("/teacher/submited_assignment_list/view_assignment/download/", data);
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

            if (response.getCode()==200){


                Map<String, Object> responseDataMap= (Map<String, Object>) response.getData();
                byte[] byteArray = (byte[]) responseDataMap.get("byteArray");
                String fileName= (String) responseDataMap.get("fileName");

                File file= new File(ClientConfig.fileRecivePath);
                FileUtilClient fileUtilClient= new FileUtilClient(file);
                fileUtilClient.clientReciveFile(byteArray, "/"+fileName);

                messageLabel.setText("File Download Successfull!!  "+ ClientConfig.fileRecivePath+fileName);

            }else {
                messageLabel.setText("File Download Unsuccessfull!!");
            }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void submitAssignmentMarks(ActionEvent actionEvent) {
        int mark = Integer.parseInt(assignMarkTextField.getText());
        String stdID = studentIDLabel.getText();
        String stdName = studentNameLabel.getText();
        String asNo = assignmentNoLabel.getText();
        String asName = assignmentTitleLabel.getText();
        if(assignMarkTextField.getText() != null){

            Map<String, Object> data= new LinkedHashMap<>();
            data.put("studentId", stdID);
            data.put("studentName", stdName);
            data.put("assignmentID", asNo);
            data.put("assignmentName", asName);
            data.put("mark", mark);

            Request request= new Request("/teacher/submited_assignment_list/view_assignment/mark/", data);

            RequestSender requestSender= new RequestSender();
            try {
                requestSender.sendRequest(request, response -> {

                    if (response.getCode()==200){
                        messageLabel.setText("Marks Added Successfully");
                    }
                    if(response.getCode()==400){
                        messageLabel.setText("Marks Already Given before");
                    }


                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else {
            messageLabel.setText("Please give mark in the mark field. ");
        }
    }





}
