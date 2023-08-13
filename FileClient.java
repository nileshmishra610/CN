import java.io.*;
import java.net.*;
import java.util.*;

public class FileClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "10.58.25.149";
        int serverPort = 12345;
        Socket socket = new Socket(serverAddress, serverPort);
        // Output stream to send data to server
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
       
		Scanner sc=new Scanner(System.in);
		String X=sc.next();
		// File fileToSend = new File("C:\\Users\\HP\\Desktop\\Aurdino.txt");
		File fileToSend=new File(X);
        String fileName = fileToSend.getName();
        // Write file name and size to server
        dataOutputStream.writeUTF(fileName);
        dataOutputStream.writeLong(fileToSend.length());
        // Input stream to read file data
        FileInputStream fileInputStream = new FileInputStream(fileToSend);
        byte[] buffer = new byte[1024];
        int bytesRead;
        // Start reading and sending file data
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytesRead);
        }
        System.out.println("File sent successfully.");
        fileInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}
