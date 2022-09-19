package client.util;

import java.io.*;

public class FileUtilClient {

     private File path;
     private String extention;

     public FileUtilClient(String path){
         this.path = new File(path);
         String fileName = this.path.getName();
         this.extention = fileName.substring(fileName.lastIndexOf('.') + 1);
     }

     public FileUtilClient(File dirPath){
         this.path= dirPath;
         if (!this.path.exists()){
             this.path.mkdirs();
         }
     }


    public byte[] clientSendFile() throws IOException {

        byte [] bytearray  = new byte [(int)path.length()];
        FileInputStream fileIn= new FileInputStream(path);
        BufferedInputStream bufferedIn= new BufferedInputStream(fileIn);
        bufferedIn.read(bytearray, 0, bytearray.length);

        return bytearray;
    }

    public void clientReciveFile(byte[] byteArray, String fileName) throws IOException {

        FileOutputStream fileOut= new FileOutputStream(path+fileName);
        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
        bufferedOut.write(byteArray);
        bufferedOut.close();
    }


    public String getExtention(){
         return extention;
    }

}
