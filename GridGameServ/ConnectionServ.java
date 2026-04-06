import java.net.*;
import java.io.*;

public class ConnectionServ {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public static void createInstance() throws IOException {
        ConnectionServ serveur = new ConnectionServ();
        serveur.start(8880);
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine = in.readLine();
        if (inputLine.equalsIgnoreCase("stop")) {
            System.out.println("bien joué!");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
