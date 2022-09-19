package client.ui.controller;

import client.RequestSender;
import client.util.ClientConfig;
import client.util.FileUtilClient;
import com.request_response.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AssignmentController implements Initializable {
    @FXML
    public Label assignmentNoLabel;
    @FXML
    public Label assignmentTitleLabel;

    public String assignmentNo;
    @FXML
    public Label publishedDateLabel;
    @FXML
    public Label submitDateLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showSelectedAssignment(String asNo, String asTitle, LocalDate pubDate, LocalDate subDate){
        assignmentNoLabel.setText(asNo);
        assignmentTitleLabel.setText(asTitle);
        publishedDateLabel.setText(String.valueOf(pubDate));
        submitDateLabel.setText(String.valueOf(subDate));
    }


    public void downloadAssignment(ActionEvent actionEvent) {

        assignmentNo = assignmentNoLabel.getText();
        Map<String, Object> data= new LinkedHashMap<>();
        data.put("asNo", assignmentNo);

        Request request= new Request("/teacher/assignment_list/download_assignment/", data);

        RequestSender requestSender = new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                Map<String, Object> responseDataMap= (Map<String, Object>) response.getData();
                byte[] byteArray = (byte[]) responseDataMap.get("byteArray");
                String fileNo= (String) responseDataMap.get("fileName");
                String extention= (String) responseDataMap.get("extention");

                File file= new File(ClientConfig.fileRecivePath);
                FileUtilClient fileUtilClient= new FileUtilClient(file);
                fileUtilClient.clientReciveFile(byteArray, "/Assignment_"+fileNo+"."+extention);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
