package ru.nsu.netesov.lab5.view;

import ru.nsu.netesov.lab5.utility.ErrorMessage;
import ru.nsu.netesov.lab5.utility.Message;
import ru.nsu.netesov.lab5.utility.server.ServerConfirm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectView extends JDialog {

    public ConnectView(String title, boolean modal, ChatView client) {
        super(client, title, modal);
        setBounds(750, 250, 750, 100);
        setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        JLabel nameLabel = new JLabel("Enter your name");
        JTextField nameField = new JTextField();
        JButton loginButton = new JButton("Сonnect to the server");
        JLabel infoLabel = new JLabel();

        nameLabel.setPreferredSize(new Dimension(70, 30));
        nameField.setPreferredSize(new Dimension(300, 30));
        loginButton.setPreferredSize(new Dimension(300, 30));
        infoLabel.setPreferredSize(new Dimension(70, 30));

        add(nameLabel);
        add(nameField);
        add(loginButton);
        add(infoLabel);

        loginButton.addActionListener(e -> {
            if (!nameField.getText().trim().isEmpty()) {
                client.setUserName(nameField.getText());
                client.setTitle(nameField.getText()+"'s chat");
                client.sendMessage(client.getConstructor().createLoginMessage(client.getUserName()));
                while (true) {
                    if (client.getDataInput().hasNext()) {
                        Message message = client.getPars().parseCommand(client.getDataInput().nextLine());
                        if (message.getType().equals("error")) {
                            ErrorMessage typedMessage = (ErrorMessage) message;
                            infoLabel.setText(typedMessage.getException());
                            break;
                        } else if (message.getType().equals("connection")) {
                            ServerConfirm typedMessage = (ServerConfirm) message;
                            client.setID(typedMessage.getID());
                            dispose();
                        }
                        break;
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    client.sendMessage(client.getConstructor().createRefuseMessage());
                    System.exit(1);
            }
        });
        setVisible(true);
    }
}
