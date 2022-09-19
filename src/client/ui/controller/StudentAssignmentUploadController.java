package client.ui.controller;

import client.RequestSender;
import client.util.ClientConfig;
import client.util.FileUtilClient;
import com.google.gson.Gson;
import com.request_response.Request;
import common.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentAssignmentUploadController implements Initializable {


    @FXML
    public Label assignmentNoLabel;
    @FXML
    public Label assignmentTitleLabel;
    @FXML
    public Label publishedDateLabel;
    @FXML
    public Label submitDateLabel;
    @FXML
    public Label messageLabel;
    @FXML
    public TextField assignmentUploadPath;

    public String assignmentFilePath;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void downloadAssignmentFile(ActionEvent actionEvent) {

        Map<String, Object> data= new LinkedHashMap<>();
        data.put("filePath", assignmentFilePath);

        Request request= new Request("/student/assignment/download/", data);
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                Map<String, Object> responseDataMap= (Map<String, Object>) response.getData();
                byte[] byteArray = (byte[]) responseDataMap.get("byteArray");
                String fileName = (String) responseDataMap.get("fileName");
                String extention = (String) responseDataMap.get("extention");

                File file= new File(ClientConfig.fileRecivePath);
                FileUtilClient fileUtilClient= new FileUtilClient(file);
                fileUtilClient.clientReciveFile(byteArray, "/Assignment_"+fileName+"."+extention);

                String location= ClientConfig.fileRecivePath+"/Assignment_"+fileName+"."+extention;
                File fileloc= new File(location);

                if(fileloc.exists()){
                    messageLabel.setText("Download Successfull!!! "+ location);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void chooseFileToUpload(ActionEvent actionEvent) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Any File", "*.*"),
                new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Doc File", "*.docx"),
                new FileChooser.ExtensionFilter("Text", "*.txt"));

        File selectedFilePath = fileChooser.showOpenDialog(null);

        if (selectedFilePath != null){
            assignmentUploadPath.setText(selectedFilePath.getAbsolutePath());
        }
    }


    public void submitAssignmentFile(ActionEvent actionEvent) throws IOException {

        String path = assignmentUploadPath.getText();
        FileUtilClient sendFile= new FileUtilClient(path);
        byte[] byteArray = sendFile.clientSendFile();
        String extention = sendFile.getExtention();
        String AssignmentNo = assignmentNoLabel.getText();

        try {
            FileReader fileReader = new FileReader("current_user_student.json");
            Gson gson= new Gson();
            user = gson.fromJson(fileReader, User.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String, Object> data= new LinkedHashMap<>();
        data.put("byteArray", byteArray);
        data.put("extention", extention);
        data.put("user", user);
        data.put("AssignmentNo", AssignmentNo);


        Request request= new Request("/student/assignment/upload/", data);
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                if(response.getCode()==200){
                    messageLabel.setText("Assignment Submition Successfull!!");
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public void showSelectedAssignment(String assignmentId, String assignmentName, LocalDate publishedDate, LocalDate submitdDate, String assignmentFilePath){
        assignmentNoLabel.setText(assignmentId);
        assignmentTitleLabel.setText(assignmentName);
        publishedDateLabel.setText(String.valueOf(publishedDate));
        submitDateLabel.setText(String.valueOf(submitdDate));
        this.assignmentFilePath = assignmentFilePath;
    }

}
