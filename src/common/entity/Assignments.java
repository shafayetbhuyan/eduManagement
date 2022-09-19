package common.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import common.entity.Assignment;
import server.util.ServerConfig;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Assignments {

    private ArrayList<Assignment> assignmentList;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File path = new File(ServerConfig.assignmentObjectPath + "AssignmentList_Object.json");

    public Assignments() {
        try {
            Type listType = new TypeToken<ArrayList<Assignment>>(){}.getType();


                FileReader fr = new FileReader(path);
                this.assignmentList = gson.fromJson(fr, listType);
                fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }


    public ArrayList<String> getAllAssignmentName(){
    ArrayList<String> allAssignmentName = new ArrayList<>();

    assignmentList.forEach(assignment -> allAssignmentName.add(assignment.getAssignmentName()));

    return allAssignmentName;
    }

    public ArrayList<String> getAllAssignmentNo(){
        ArrayList<String> allAssignmentNo = new ArrayList<>();

        assignmentList.forEach(assignment -> allAssignmentNo.add(assignment.getAssignmentId()));

        return allAssignmentNo;
    }






//    public Assignments() {
//        assignmentList= new ArrayList<>();
//
//        getAllAssignmentObject().forEach(str->{
//            String[] split = str.split("\\|");
//            String assignmentId= split[0];
//            String assignmentName = split[1];
//            String assignmentSubject = split[2];
//            String assignmentDetails = split[3];
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//            LocalDate publishedDate = LocalDate.parse(split[4], formatter);
//            LocalDate SubmitDate = LocalDate.parse(split[5], formatter);
//
//            assignmentList.add(new Assignment(assignmentId, assignmentName, assignmentSubject,assignmentDetails, publishedDate, SubmitDate));
//        });
//    }
//
//
//
//    public List<Assignment> getAssignmentList() {
//        return assignmentList;
//    }
//
//
//
//    public ArrayList<String> getAllAssignmentID(){
//        ArrayList<String> allAssignmentID = new ArrayList<>();
//
//        getAllAssignmentObject().forEach(str->{
//            String[] split = str.split("\\|");
//            String assignmentId= split[0];
//
//            allAssignmentID.add(assignmentId);
//        });
//
//        return allAssignmentID;
//    }
//
//
//
//    public ArrayList<String> getAllAssignmentName(){
//        ArrayList<String> allAssignmentName = new ArrayList<>();
//
//        getAllAssignmentObject().forEach(str->{
//            String[] split = str.split("\\|");
//            String assignmentId= split[1];
//
//            allAssignmentName.add(assignmentId);
//        });
//
//        return allAssignmentName;
//    }
//
//
//
//    public ArrayList<String> getAllAssignmentObject() {
//        String line;
//        ArrayList<String> strList= new ArrayList<>();
//        try {
//            BufferedReader in= new BufferedReader(new FileReader(path));
//            String temp="";
//            while ((line= in.readLine())!=null){
//                if (!line.equals("#")){
//                    temp+=line;
//                }else {
//                    strList.add(temp);
//                    temp="";
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return strList;
//    }


}
