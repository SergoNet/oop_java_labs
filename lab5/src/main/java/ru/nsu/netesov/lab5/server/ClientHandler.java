package ru.nsu.netesov.lab5.server;


import ru.nsu.netesov.lab5.utility.ListOfUsers;
import ru.nsu.netesov.lab5.utility.Message;
import ru.nsu.netesov.lab5.utility.client.ClientMessage;
import ru.nsu.netesov.lab5.utility.client.LoginToTheSystem;
import ru.nsu.netesov.lab5.utility.client.LogoutToTheSystem;
import ru.nsu.netesov.lab5.parser.Constructor;
import ru.nsu.netesov.lab5.parser.Parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Parser pars = new Parser();
    private final Constructor constructor = new Constructor();

    private static final LinkedList<String> nameUser = new LinkedList<>();
    private final String ID = String.valueOf(new Random().nextInt());
    private String busyName;
    private Scanner dataInput;
    private PrintWriter dataOutput;
    private Server server;
    private Socket socket;

    @Override
    public void run() {
        try {
            setConnection();
            while (!Thread.currentThread().isInterrupted()) {
                if (dataInput.hasNext()) {
                    Message message = pars.parseCommand(dataInput.nextLine());
                    if (message.getType().equals("client_message")) {
                        ClientMessage typedMessage = (ClientMessage) message;
                        if (ID.equals(typedMessage.getID())) {
                            server.sendingMessages(constructor.createServerMessage(typedMessage.getContent(), busyName));
                        }
                    }
                    if (message.getType().equals("logout")) {
                        LogoutToTheSystem typedMessage = (LogoutToTheSystem) message;
                        if (ID.equals(typedMessage.getID())) {
                            synchronized (nameUser){
                                nameUser.remove(busyName);
                            }
                            server.removeUserController(this);
                            Thread.currentThread().interrupt();
                            server.sendingMessages(constructor.createUserExitMessage(busyName));
                            close();
                        }
                    }
                    if (message.getType().equals("getlist")) {
                        ListOfUsers typedMessage = (ListOfUsers) message;
                        if (ID.equals(typedMessage.getID())) {
                            sendMessage(constructor.createUsersListMessage(nameUser));
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setConnection() throws IOException {
        while (!Thread.currentThread().isInterrupted()) {
            if (dataInput.hasNext()) {
                Message message = pars.parseCommand(dataInput.nextLine());
                System.out.println(message.getType());
                if (message.getType().equals("login")) {
                    LoginToTheSystem typedMessage = (LoginToTheSystem) message;
                    busyName = typedMessage.getName();
                    if (nameUser.contains(busyName)) {
                        System.out.println(busyName);
                        sendMessage(constructor.createErrorMessage("User already exists"));
                    } else {
                        synchronized (nameUser) {
                            nameUser.add(busyName);
                        }
                        sendMessage(constructor.createConfirmMessage(ID));
                        server.sendingMessages(constructor.createNewUserMessage(busyName));
                        break;
                    }
                } else if (message.getType().equals("refuse")) {
                    close();
                    server.getUserController().remove(this);
                    Thread.currentThread().interrupt();
                } else {
                    sendMessage(constructor.createErrorMessage("Please repeat again"));
                }
            }
        }
    }

    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            dataInput = new Scanner(socket.getInputStream());
            dataOutput = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sendMessage(String message) {
        try {
            dataOutput.println(message);
            dataOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        dataInput.close();
        dataOutput.close();
        socket.close();
    }
}
