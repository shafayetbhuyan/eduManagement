package server.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.request_response.Response;
import common.entity.*;
import server.RequestHandler;
import server.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentController implements IController {

    List<Marks> mrlist;

    @Override
    public void addMappings() {
        RequestHandler.addMappin("/student/assignment_list", request -> {

            Assignments assignments= new Assignments();

            Response response= new Response();
            response.setData(assignments.getAllAssignmentName());

            return response;
        });


        RequestHandler.addMappin("/student/assignment_list/view_assignment/", request -> {
            Map<String, Object> requestedDataMap = request.getData();
            String asTitle = (String) requestedDataMap.get("asTitle");

            Assignments assignments= new Assignments();
            Optional<Assignment> selectedAssignment = assignments
                    .getAssignmentList()
                    .stream()
                    .filter(assignment -> asTitle.equals(assignment.getAssignmentName()))
                    .findFirst();

            Response response= new Response();
            if(selectedAssignment.isPresent()){
                response.setCode(200);
                response.setData(selectedAssignment.get());
            }
            else{
                response.setCode(404);
            }

            return response;
        });


        RequestHandler.addMappin("/student/assignment/download/", request -> {
            Response response= new Response();

            Map<String, Object> requestedDataMap = request.getData();
            String assignmentFilePath = (String) requestedDataMap.get("filePath");


            Assignments assignments= new Assignments();
            Optional<Assignment> as = assignments.
                    getAssignmentList()
                    .stream()
                    .filter(assignment -> assignmentFilePath.equals(assignment.getAssignmentFilePath()))
                    .findFirst();
            Assignment assignment = as.get();


            FileUtilServer fileUtilServer = new FileUtilServer();
            byte[] byteArray = fileUtilServer.serverSendFile(assignmentFilePath);
            String extention = fileUtilServer.getExtention();

            Map<String, Object> responseDataMap = new LinkedHashMap<>();
            responseDataMap.put("byteArray", byteArray);
            responseDataMap.put("fileName", assignment.getAssignmentId());
            responseDataMap.put("extention", extention);

            response.setData(responseDataMap);
            response.setCode(200);

            return response;
        });


        RequestHandler.addMappin("/student/assignment/upload/", request -> {

            Map<String, Object> requestedDataMap = request.getData();
            byte[] byteArray = (byte[]) requestedDataMap.get("byteArray");
            String extention = (String) requestedDataMap.get("extention");
            String assignmentNo = (String) requestedDataMap.get("AssignmentNo");
            User user = (User) requestedDataMap.get("user");
            String stdID = String.valueOf(user.getId());
            String stdfullName = user.getFullName();
            Assignments assignments= new Assignments();
            List<Assignment> assignmentList = assignments.getAssignmentList();
            Assignment assignment = assignmentList
                    .stream()
                    .filter(as -> assignmentNo.equals(as.getAssignmentId()))
                    .findFirst()
                    .get();

            String assignmentName = assignment.getAssignmentName();
            String publishedDate = String.valueOf(assignment.getPublishedDate());
            String submitDate = String.valueOf(assignment.getSubmitDate());


            String asFileName = "/AsNo_"+assignmentNo+"_std_"+stdID+"."+extention;
            FileUtilServer reciveFile= new FileUtilServer(ServerConfig.stdSubmitedAssignmentPath);
            reciveFile.serverReciveFile(byteArray, asFileName);

            String absulatePath = ServerConfig.stdSubmitedAssignmentPath+asFileName;

            SubmitedAssignment submitedAssignment= new SubmitedAssignment(stdfullName, stdID, assignmentNo, assignmentName, publishedDate, submitDate, absulatePath);
            String asObjectPath = ServerConfig.stdSubmitedAssignmentObjectpath + "Student_Submited_Assignment_Object_Path.json";
            File dirPath = new File(ServerConfig.stdSubmitedAssignmentObjectpath);
            File objJsonFilePath = new File(asObjectPath);

            if(!dirPath.exists()){
                dirPath.mkdirs();
                objJsonFilePath.createNewFile();
            }else {
                objJsonFilePath.createNewFile();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<SubmitedAssignment>>(){}.getType();

            FileReader fr = new FileReader(objJsonFilePath);
//            BufferedReader buffReader = new BufferedReader(fr);
            List<SubmitedAssignment> subAsList = gson.fromJson(fr, listType);
            fr.close();


            if(null==subAsList) {
                subAsList = new ArrayList<>();
            }

            subAsList.add(submitedAssignment);
            FileWriter fw  = new FileWriter(objJsonFilePath);
//            BufferedWriter buffWriter = new BufferedWriter(fw);
            gson.toJson(subAsList, fw);
            fw.close();



            Response response= new Response(200);

            return response;
        });


        RequestHandler.addMappin("/student/marks/", request -> {

            Response response= new Response();

            Map<String, Object> requestedDataMap = request.getData();
            User user = (User)requestedDataMap.get("user");
            Integer userId = user.getId();



            String asObjectPath = ServerConfig.assignmentObjectPath + "Assignment_Marks_Object.json";

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Marks>>(){}.getType();

            File objJsonFilePath = new File(asObjectPath);

            if (!objJsonFilePath.exists()){
                response.setCode(404);
            }else {
                FileReader fr = new FileReader(objJsonFilePath);
                mrlist = gson.fromJson(fr, listType);
                fr.close();
            }

            List<Marks> studentMarks = mrlist
                    .stream()
                    .filter(m -> m.getStudentID().equals(String.valueOf(userId)))
                    .collect(Collectors.toList());

            if (!studentMarks.isEmpty()){
                response.setData(studentMarks);
                response.setCode(200);
            }else {
                response.setCode(404);
            }

            return response;
        });



    }


}
