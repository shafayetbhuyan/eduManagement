package common.entity;


import client.ui.controller.LoginController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import server.util.ServerConfig;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Check {
    public static void main(String[] args) throws IOException {
//        File path= new File("E:/FileTransfer/files/server/client/assignment_list.txt");
//
//        BufferedReader in= new BufferedReader(new FileReader(path));
//        String line;
//
//        ArrayList<String> strList= new ArrayList<>();
//        String temp="";
//        while ((line= in.readLine())!=null){
//            if (!line.equals("#")){
//                temp+=line;
//            }else {
//                strList.add(temp);
//                temp="";
//            }
//        }
//
//        ArrayList<String> asList= new ArrayList<>();
//        strList.forEach(str->{
//            String[] split = str.split("\\|");
//            asList.add(split[1]);
//        });
//
//        asList.forEach(e->{
//            System.out.println(e);
//        });
//

        String s = UUID.randomUUID().toString();
        long l = System.currentTimeMillis()/1000;
        System.out.println(l);


        String fileName = "file.date.txt";
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

        System.out.println(ext);

        File file= new File("/fjoia/ihifai /AmarName.pdf");
        System.out.println(file.getName());

//=========================================================================================
//
//
//        LocalDate aaa = LocalDate.now();
//        LocalDate a = LocalDate.now();
//        Assignment assi = new Assignment("f", "f", "f", "f", aaa, a);
//        File path = new File("newTestGSON.json");
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            // construct Type that tells Gson about the generic type
//            Type dtoListType = new TypeToken<List<Assignment>>(){}.getType();
//
//            FileReader fr = new FileReader(path);
//            List<Assignment> dtos = gson.fromJson(fr, dtoListType);
//            fr.close();
//
//            // If it was an empty one create initial list
//
//            if(null==dtos) {
//                dtos = new ArrayList<>();
//            }
//
//            // Add new item to the list
//            dtos.add(assi);
//            // No append replace the whole file
//            FileWriter fw  = new FileWriter(path);
//            gson.toJson(dtos, fw);
//            fw.close();
// ============================================================================================

//        String asObjectPath = ServerConfig.assignmentObjectPath + "AssignmentList_Object.json";
//
//        String asFileName = "/Assignment_"+10+".aaa";
//
//
//        System.out.println(ServerConfig.assignmentSavePath+asFileName);

//===========================================================================================

//        String asObjectPath = ServerConfig.assignmentObjectPath + "AssignmentList_Object.json";
//
//
//        File path = new File(asObjectPath);
//        System.out.println(path.getParentFile());

//====================================================================================
//        File path= new File(ServerConfig.assignmentObjectPath + "AssignmentList_Object.json");
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Type listType = new TypeToken<ArrayList<Assignment>>(){}.getType();
//
//        FileReader fr = new FileReader(path);
//        ArrayList<Assignment> aslist = gson.fromJson(fr, listType);
//        fr.close();
//
//        for(Assignment a: aslist){
//            LocalDate submitDate = a.getSubmitDate();
//        }

//====================================================================================

//        try {
//            FileReader fileReader= new FileReader("current_user_student.json");
//            Gson gson= new Gson();
//            User user = gson.fromJson(fileReader, User.class);
//
//             System.out.println(user.toString());
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//=============================================================================
        String path = "E:/FileTransfer/files/server/client/teacher/assignments/Assignment_1637753564.txt";

        System.out.println(path.substring(path.lastIndexOf('/') + 1));

    }


}
