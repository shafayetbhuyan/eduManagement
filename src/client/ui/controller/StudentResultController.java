package client.ui.controller;

import client.RequestSender;
import client.util.FileUtilClient;
import com.google.gson.Gson;
import com.request_response.Request;
import common.entity.Marks;
import common.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentResultController implements Initializable {
    public TableView<Marks> tabViewMarks;
    public TableColumn<Marks, String> tabColAssignmentName;
    public TableColumn<Marks, String> tabColAssignmentNo;
    public TableColumn<Marks, Integer> tabColMarks;
    public Label messageLabel;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FileReader fileReader = new FileReader("current_user_student.json");
            Gson gson= new Gson();
            user = gson.fromJson(fileReader, User.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String, Object> data= new LinkedHashMap<>();
        data.put("user", user);

        Request request= new Request("/student/marks/", data);
        RequestSender requestSender= new RequestSender();
        try {
            requestSender.sendRequest(request, response -> {

                if(response.getCode() == 200){
                    List<Marks> marksList = (List<Marks>) response.getData();


                    ObservableList<Marks> marks= FXCollections.observableArrayList(marksList);

                    tabColAssignmentNo.setCellValueFactory(new PropertyValueFactory<Marks, String>("assignmentID"));
                    tabColAssignmentName.setCellValueFactory(new PropertyValueFactory<Marks, String>("assignmentName"));
                    tabColMarks.setCellValueFactory(new PropertyValueFactory<Marks, Integer>("mark"));

                    tabViewMarks.setItems(marks);
                }else {
                    messageLabel.setText("Marks hasn't been given yet!!!");
                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
