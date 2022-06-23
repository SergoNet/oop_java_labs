package ru.nsu.netesov.lab5.view;

import ru.nsu.netesov.lab5.utility.Message;
import ru.nsu.netesov.lab5.parser.Constructor;
import ru.nsu.netesov.lab5.parser.Parser;
import ru.nsu.netesov.lab5.utility.server.ServerList;
import ru.nsu.netesov.lab5.utility.server.ServerMessage;
import ru.nsu.netesov.lab5.utility.server.ServerNewUser;
import ru.nsu.netesov.lab5.utility.server.ServerUserLogout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ChatView extends JFrame {
    private final Parser pars = new Parser();
    private final Constructor constructor = new Constructor();

    private LinkedList<String> listOfUsers;
    private static final String hostName = "localhost";
    private static final int hostPort = 3000;
    private String ID;
    private String userName;

    private Scanner dataInput;
    private PrintWriter dataOutput;
    private Thread handler;

    public ChatView() {
        try {
            Socket socket = new Socket(hostName, hostPort);
            dataInput = new Scanner(socket.getInputStream());
            dataOutput = new PrintWriter(socket.getOutputStream());
            listOfUsers = new LinkedList<>();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        setBounds(400, 200, 800, 500);
        setLayout(new BorderLayout());

        JTextArea chat = new JTextArea();
        chat.setLineWrap(true);
        chat.setEditable(false);
        JScrollPane chatPanel = new JScrollPane(chat);
        chatPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chatPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatPanel.setPreferredSize(new Dimension(600, 400));
        add(chatPanel, BorderLayout.CENTER);

        JTextArea users = new JTextArea();
        JScrollPane usersScrollPane = new JScrollPane(users);
        users.setEditable(false);
        usersScrollPane.setPreferredSize(new Dimension(200, 400));
        usersScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        usersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(usersScrollPane, BorderLayout.EAST);

        Container sender = new Container();
        sender.setLayout(new BorderLayout());

        JTextArea text = new JTextArea();
        JScrollPane textMessageScrollPane = new JScrollPane(text);
        textMessageScrollPane.setPreferredSize(new Dimension(600, 50));
        textMessageScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textMessageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sender.add(textMessageScrollPane, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send a message");
        JButton exitButton = new JButton("Leave the chat");
        sendButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setPreferredSize(new Dimension(200, 50));
        sender.add(exitButton, BorderLayout.SOUTH);
        sender.add(sendButton, BorderLayout.EAST);
        add(sender, BorderLayout.SOUTH);

        ConnectView entryDialog = new ConnectView("Login", true, this);
        exitButton.addActionListener(e -> {
            handler.interrupt();
            sendMessage(constructor.createUserLogoutMessage(ID));
            close();
        });
        listOfUsers = getListOfUsers();
        userList(users);
        sendButton.addActionListener(e -> {
            if (!text.getText().trim().isEmpty()) {
                String message = constructor.createClientMessage(text.getText(), ID);
                sendMessage(message);
                text.selectAll();
                text.replaceSelection("");
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    handler.interrupt();
                    sendMessage(constructor.createUserLogoutMessage(ID));
                    close();
            }
        });
        start(chat, users);
        setVisible(true);
    }

    private void start(JTextArea chat, JTextArea users){
        handler = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (dataInput.hasNext()) {
                    Message message = pars.parseCommand(dataInput.nextLine());
                    System.out.println(message.getType());
                    if (message.getType().equals("server_message")) {
                        ServerMessage typedMessage = (ServerMessage) message;
                        chat.append(typedMessage.getName() + ":" + "\n" + typedMessage.getContent());
                        chat.append("\n");
                    }
                    if (message.getType().equals("userlogin")) {
                        ServerNewUser typedMessage = (ServerNewUser) message;
                        listOfUsers.add(typedMessage.getName());
                        userList(users);
                    }
                    if (message.getType().equals("userlogout")) {
                        ServerUserLogout typedMessage = (ServerUserLogout) message;
                        listOfUsers.remove(typedMessage.getName());
                        userList(users);
                    }
                }
            }
        });
        handler.start();
    }

    synchronized private LinkedList<String> getListOfUsers() {
        sendMessage(constructor.createGetUsersMessage(ID));
        while (true) {
            if (dataInput.hasNext()) {
                Message message = pars.parseCommand(dataInput.nextLine());
                if (message.getType().equals("list")) {
                    ServerList typedMessage = (ServerList) message;
                    return typedMessage.getUsers();
                }
            }
        }
    }

    synchronized private void userList(JTextArea users){
        users.selectAll();
        users.replaceSelection("");
        users.append("Users on the network:" + "\n");
        listOfUsers.forEach(user -> users.append(user +"\n"));
    }

    public void sendMessage(String message) {
        try {
            dataOutput.println(message);
            dataOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        dataInput.close();
        dataOutput.close();
        System.exit(1);
    }

    public Parser getPars() { return pars;}
    public Constructor getConstructor() { return constructor; }
    public String getUserName() { return userName; }
    public Scanner getDataInput() { return dataInput; }
    public void setID(String ID) { this.ID = ID; }
    public void setUserName(String userName) { this.userName = userName; }
}
