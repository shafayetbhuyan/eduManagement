package server.util;

import java.io.*;

public class FileUtilServer {

    private File dirPath;
    private String extention;

    public FileUtilServer(){}

    public FileUtilServer(String dirPath){
        this.dirPath = new File(dirPath);
        if (!this.dirPath.exists()){
            this.dirPath.mkdirs();
        }
    }

    public void serverReciveFile(byte[] byteArray, String fileName) throws IOException {

        FileOutputStream fileOut= new FileOutputStream(dirPath+fileName);
        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
        bufferedOut.write(byteArray);
        bufferedOut.close();
    }


    public byte[] serverSendFile(String path) throws IOException {
        File f =new File(path);
        byte [] bytearray  = new byte [(int)f.length()];
        FileInputStream fileIn= new FileInputStream(f);
        BufferedInputStream bufferedIn= new BufferedInputStream(fileIn);
        bufferedIn.read(bytearray, 0, bytearray.length);


        this.extention = path.substring(path.lastIndexOf('.') + 1);


        return bytearray;
    }

    public String getExtention(){
        return this.extention;
    }


}
