package client.ui.controller;

import client.RequestSender;
import client.util.FileUtilClient;
import com.request_response.Request;
import common.entity.Assignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CreateAssignmentController implements Initializable {

    @FXML
    public TextField assignmentNoTextField;
    @FXML
    public TextField assignmentTitleTextField;
    @FXML
    public TextField assignmentSubjectTextField;
    @FXML
    public TextField assignmentFilePathTextField;
    @FXML
    public DatePicker publishDatePicker;
    @FXML
    public DatePicker submitDatePicker;
    @FXML
    public Label messageLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        long l = System.currentTimeMillis()/1000;
        assignmentNoTextField.setText(String.valueOf(l));
    }

    public void createAssignmentButton(ActionEvent actionEvent) throws Exception {
        String asNo = assignmentNoTextField.getText();
        String asTitle = assignmentTitleTextField.getText();
        String asSubject = assignmentSubjectTextField.getText();
        String asFilePath = assignmentFilePathTextField.getText();
//        String extention = asFilePath.substring(asFilePath.lastIndexOf('.') + 1);
        String extention = new FileUtilClient(asFilePath).getExtention();
        LocalDate publishDate = publishDatePicker.getValue();
        LocalDate submitDate = submitDatePicker.getValue();





//        File path= new File(asFilePath);
//        byte [] bytearray  = new byte [(int)path.length()];
//        FileInputStream fileIn= new FileInputStream(path);
//        BufferedInputStream bufferedIn= new BufferedInputStream(fileIn);
//        bufferedIn.read(bytearray, 0, bytearray.length);

        FileUtilClient sendFile= new FileUtilClient(asFilePath);
        byte[] bytearray = sendFile.clientSendFile();


        Map<String, Object> data = new LinkedHashMap<>();
        data.put("asNo", asNo);
        data.put("asTitle", asTitle);
        data.put("asSubject", asSubject);
        data.put("extention", extention);
        data.put("publishDate", publishDate);
        data.put("submitDate", submitDate);
        data.put("byteArray", bytearray);

        Request request= new Request("/teacher/create_assignment/submit", data);
        RequestSender requestSender= new RequestSender();

        if (asNo.isEmpty() || asTitle.isEmpty() || asSubject.isEmpty() || asFilePath.isEmpty()|| publishDate==null || submitDate==null){
            messageLabel.setText("Please fill-up all the information");
        }else {

            requestSender.sendRequest(request, response -> {
                if (response.getCode()==200){
                    messageLabel.setText("Assignment created successfully");

                    assignmentNoTextField.clear();
                    assignmentTitleTextField.clear();
                    assignmentSubjectTextField.clear();
                    assignmentFilePathTextField.clear();
                    publishDatePicker.setValue(null);
                    submitDatePicker.setValue(null);
                }else {
                    messageLabel.setText("Unsuccessfull");
                }
            });

        }

    }



    public void publishedDateAction(ActionEvent actionEvent) {}

    public void submitDateAction(ActionEvent actionEvent) {}

    public void chooseAssignmentButton(ActionEvent actionEvent) {

        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Any File", "*.*"),
                new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Doc File", "*.docx"),
                new FileChooser.ExtensionFilter("Text", "*.txt"));

        File selectedFilePath = fileChooser.showOpenDialog(null);

        if (selectedFilePath != null){
            assignmentFilePathTextField.setText(selectedFilePath.getAbsolutePath());
        }

    }
}
