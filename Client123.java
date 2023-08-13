import java.io.*;
import java.net.*;

public class Client123 {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("10.58.25.149", 12345); 
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
     }
}
}