package server.Controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.request_response.Response;
import common.entity.*;
import server.RequestHandler;
import server.util.FileUtilServer;
import server.util.ServerConfig;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TeacherController implements IController {


    @Override
    public void addMappings() {
        RequestHandler.addMappin("/teacher/students", request -> {

            Users users = new Users();
            List<User> students = users.getUserList().stream().filter(user -> user.getType().equals(1)).collect(Collectors.toList());
            Response response= new Response();
            response.setData(students);
            return response;
        });

        RequestHandler.addMappin("/teacher/create_assignment/submit", request -> {

            Response response= new Response(200);
            Map<String, Object> requestDataMap = request.getData();
            String asNo = requestDataMap.get("asNo").toString();
            String asTitle = requestDataMap.get("asTitle").toString();
            String asSubject = requestDataMap.get("asSubject").toString();
            String asExtention = requestDataMap.get("extention").toString(); // For Saving file only
            LocalDate publishDate = (LocalDate) requestDataMap.get("publishDate");
//            String strPublishDate = publishDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            LocalDate submitDate = (LocalDate) requestDataMap.get("submitDate");
//            String strsubmitDate = submitDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            byte[] byteArray= (byte[]) requestDataMap.get("byteArray");


//            String assignmentData= asNo+ "|" +asTitle+ "|" +asSubject+ "|" +asExtention+ "|" +strPublishDate+ "|" +strsubmitDate+"\n"+"#";
//            try {
//                File path= new File(ServerConfig.serverFileDirectory+"client");
//                String filePath;
//                if(!path.exists()){
//                    path.mkdirs();
//                    filePath = path.getAbsolutePath()+"/assignment_list.txt";
//                }else {
//                    filePath = path.getAbsolutePath()+"/assignment_list.txt";
//                }
//                FileWriter fileWriter= new FileWriter(filePath, true);
//                PrintWriter printWriter= new PrintWriter(fileWriter, true);
//                printWriter.println(assignmentData);
//
//                fileWriter.close();
//                printWriter.close();


//            File assignmentFile= new File(ServerConfig.assignmentSavePath);
//            if (!assignmentFile.exists()){
//                assignmentFile.mkdirs();
//            }

//                FileOutputStream fileOut= new FileOutputStream(assignmentFile+"/Assignment_"+asNo+"."+asExtention);
//                BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
//                bufferedOut.write(byteArray);
//                bufferedOut.close();

//        }catch (Exception e){
//            e.printStackTrace();
//        }



            String asFileName = "/Assignment_"+asNo+"."+asExtention;
            FileUtilServer reciveFile= new FileUtilServer(ServerConfig.assignmentSavePath);
            reciveFile.serverReciveFile(byteArray, asFileName);


            String asObjectPath = ServerConfig.assignmentObjectPath + "AssignmentList_Object.json";

            Assignment assignment = new Assignment(asNo, asTitle, asSubject, ServerConfig.assignmentSavePath+asFileName, publishDate, submitDate);
            File dirPath = new File(ServerConfig.assignmentObjectPath);
            File objJsonFilePath = new File(asObjectPath);

            if(!dirPath.exists()){
                dirPath.mkdirs();
                objJsonFilePath.createNewFile();
            }else {
                objJsonFilePath.createNewFile();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // construct Type that tells Gson about the generic type
            Type listType = new TypeToken<List<Assignment>>(){}.getType();

            FileReader fr = new FileReader(objJsonFilePath);
//            BufferedReader buffReader = new BufferedReader(fr);
            List<Assignment> aslist = gson.fromJson(fr, listType);
            fr.close();

            // If it was an empty one create initial list

            if(null==aslist) {
                aslist = new ArrayList<>();
            }

            // Add new item to the list
            aslist.add(assignment);
            // No append replace the whole file
            FileWriter fw  = new FileWriter(objJsonFilePath);
//            BufferedWriter buffWriter = new BufferedWriter(fw);
            gson.toJson(aslist, fw);
            fw.close();


            response.setCode(200);

//            response.setData();
            return response;
        });

        RequestHandler.addMappin("/teacher/assignment_list", request -> {


//            File path= new File(ServerConfig.serverFileDirectory+"client/assignment_list.txt");
//
//            BufferedReader in= new BufferedReader(new FileReader(path));
//            String line;
//            ArrayList<String> strList= new ArrayList<>();
//            String temp="";
//            while ((line= in.readLine())!=null){
//                if (!line.equals("#")){
//                    temp+=line;
//                }else {
//                    strList.add(temp);
//                    temp="";
//                }
//            }
//
//            ArrayList<String> asList= new ArrayList<>();
//            strList.forEach(str->{
//                String[] split = str.split("\\|");
//                asList.add(split[1]);
//            });

            //working

            Assignments assignments= new Assignments();

            Response response= new Response();
            response.setData(assignments.getAllAssignmentName());
            return response;
        });

        RequestHandler.addMappin("/teacher/assignment_list/view_assignment", request -> {

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


        RequestHandler.addMappin("/teacher/assignment_list/download_assignment/", request -> {

            Response response= new Response();

            Map<String, Object> requestedDataMap = request.getData();
            String asNo = (String) requestedDataMap.get("asNo");

            Assignments assignments = new Assignments();
            Optional<Assignment> selectedAssignment = assignments
                    .getAssignmentList()
                    .stream()
                    .filter(assignment -> asNo.equals(assignment.getAssignmentId()))
                    .findFirst();

            Assignment assignment = selectedAssignment.get();
            String assignmentFilePath = assignment.getAssignmentFilePath();

            FileUtilServer fileUtilServer = new FileUtilServer();
            byte[] byteArray = fileUtilServer.serverSendFile(assignmentFilePath);
            String extention = fileUtilServer.getExtention();

            Map<String, Object> responseDataMap = new LinkedHashMap<>();
            responseDataMap.put("byteArray", byteArray);
            responseDataMap.put("fileName", assignment.getAssignmentId());
            responseDataMap.put("extention", extention);

            response.setCode(200);
            response.setData(responseDataMap);

            return response;
        });



        RequestHandler.addMappin("/teacher/sunmited_assignments/", request -> {

            Response response = new Response();

            String asObjectPath = ServerConfig.stdSubmitedAssignmentObjectpath + "Student_Submited_Assignment_Object_Path.json";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File path = new File(asObjectPath);

            try {
                Type listType = new TypeToken<ArrayList<SubmitedAssignment>>() {
                }.getType();

                FileReader fr = new FileReader(path);
                ArrayList<SubmitedAssignment> submitedAssignments = gson.fromJson(fr, listType);

                fr.close();

                response.setData(submitedAssignments);
                response.setCode(200);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        });


        RequestHandler.addMappin("/teacher/submited_assignment_list/view_assignment/student", request -> {

            Response response= new Response();

            Map<String, Object> requestedDataMap = request.getData();
            String studentId = (String) requestedDataMap.get("studentId");
            String assignmentNo = (String) requestedDataMap.get("assignmentNo");



            String asObjectPath = ServerConfig.stdSubmitedAssignmentObjectpath + "Student_Submited_Assignment_Object_Path.json";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File path = new File(asObjectPath);

            try {
                Type listType = new TypeToken<ArrayList<SubmitedAssignment>>(){}.getType();

                FileReader fr = new FileReader(path);
                ArrayList<SubmitedAssignment> submitedAssignments = gson.fromJson(fr, listType);
                fr.close();

                SubmitedAssignment submitedAssignment = submitedAssignments
                        .stream()
                        .filter(subAs -> studentId.equals(subAs.getStudentID()) && assignmentNo.equals(subAs.getAssignmentNo()))
                        .findFirst().get();

                response.setData(submitedAssignment);
                response.setCode(200);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return response;
        });


        RequestHandler.addMappin("/teacher/submited_assignment_list/view_assignment/download/", request -> {

            Response response= new Response();

            Map<String, Object> requestedDatamap = request.getData();
            String filePath = (String) requestedDatamap.get("filePath");

            FileUtilServer fileUtilServer = new FileUtilServer();
            byte[] byteArray = fileUtilServer.serverSendFile(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

            Map<String, Object> responseDataMap = new LinkedHashMap<>();
            responseDataMap.put("byteArray", byteArray);
            responseDataMap.put("fileName", fileName);

            response.setData(responseDataMap);
            response.setCode(200);


            return response;
        });


        RequestHandler.addMappin("/teacher/submited_assignment_list/view_assignment/mark/", request -> {
            Response response= new Response();

            Map<String, Object> requestDataMap = request.getData();
            String stdID = (String) requestDataMap.get("studentId");
            String stdName =(String) requestDataMap.get("studentName");
            String asNo =(String) requestDataMap.get("assignmentID");
            String asName =(String) requestDataMap.get("assignmentName");
            int mark = (int) requestDataMap.get("mark");

            Marks marks= new Marks(stdID, stdName, asNo, asName,mark);


            String asObjectPath = ServerConfig.assignmentObjectPath + "Assignment_Marks_Object.json";

            File dirPath = new File(ServerConfig.assignmentObjectPath);
            File objJsonFilePath = new File(asObjectPath);

            if(!dirPath.exists()){
                dirPath.mkdirs();
                objJsonFilePath.createNewFile();
            }else {
                objJsonFilePath.createNewFile();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Marks>>(){}.getType();

            FileReader fr = new FileReader(objJsonFilePath);
            List<Marks> aslist = gson.fromJson(fr, listType);
            fr.close();


            if(null==aslist) {
                aslist = new ArrayList<>();
            }

                boolean b = aslist.stream().anyMatch(m -> stdID.equals(m.getStudentID()) && asNo.equals(m.getAssignmentID()));
                if (!b){
                    aslist.add(marks);
                    FileWriter fw  = new FileWriter(objJsonFilePath);
                    gson.toJson(aslist, fw);
                    fw.close();
                    response.setCode(200);
                }else {
                    response.setCode(400);
                }



            return response;
        });


        RequestHandler.addMappin("/teacher/students/marks/", request -> {

            String asObjectPath = ServerConfig.assignmentObjectPath + "Assignment_Marks_Object.json";

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type listType = new TypeToken<List<Marks>>(){}.getType();

            File objJsonFilePath = new File(asObjectPath);

            FileReader fr = new FileReader(objJsonFilePath);
            List<Marks> mrlist = gson.fromJson(fr, listType);
            fr.close();


            Response response= new Response();

            response.setCode(200);
            response.setData(mrlist);
            return response;
        });


        //more mappin to add
    }
}
