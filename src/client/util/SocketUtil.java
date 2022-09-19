package client.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketUtil {

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public SocketUtil(Socket socket) throws Exception{
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Object readSocket() throws Exception{
        return this.objectInputStream.readObject();
    }

    public void writeSocket(Object object) throws Exception{
        this.objectOutputStream.writeObject(object);
    }

    public boolean isClosed(){
        return this.socket.isClosed();
    }

    public void closeSocket() throws IOException {
        this.objectOutputStream.close();
        this.objectInputStream.close();
        this.socket.close();
    }

}
