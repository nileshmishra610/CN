import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server waiting for connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection established with client.");

        // Input stream to receive data from client
        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

        // Read file name and size from client
        String fileName = dataInputStream.readUTF();
        long fileSize = dataInputStream.readLong();

        // Output stream to write data to a file
        FileOutputStream fileOutputStream = new FileOutputStream("received_" + fileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        long totalBytesRead = 0;

        // Start receiving and writing the file data
        while ((bytesRead = dataInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;

            if (totalBytesRead == fileSize) {
                break;
            }
        }

        System.out.println("File received successfully.");
        fileOutputStream.close();
        dataInputStream.close();
        clientSocket.close();
        serverSocket.close();
    }
}
