package ru.nsu.netesov.lab5.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int hostPort = 3000;
    private final ArrayList<ClientHandler> clientController = new ArrayList<>();

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(hostPort);
        do {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientController1 = new ClientHandler(clientSocket, this);
            new Thread(clientController1).start();
            clientController.add(clientController1);
        } while (true);
    }

    public void sendingMessages(String message) {for (ClientHandler clientHandler : clientController) clientHandler.sendMessage(message); }
    public synchronized void removeUserController(ClientHandler clientHandler){ clientController.remove(clientHandler); }
    public ArrayList<ClientHandler> getUserController() { return clientController; }
}
