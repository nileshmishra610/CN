import java.io.*;
import java.net.*;

public class Server123 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Replace with the desired port number
            System.out.println("Server is listening for incoming connections...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message: " + message);
                System.out.print("Enter your response: ");
                String response = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.println(response);
            }

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}