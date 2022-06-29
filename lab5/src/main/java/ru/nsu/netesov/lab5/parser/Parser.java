package ru.nsu.netesov.lab5.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.nsu.netesov.lab5.utility.ErrorMessage;
import ru.nsu.netesov.lab5.utility.ListOfUsers;
import ru.nsu.netesov.lab5.utility.Message;
import ru.nsu.netesov.lab5.utility.SuccessMessage;
import ru.nsu.netesov.lab5.utility.client.ClientMessage;
import ru.nsu.netesov.lab5.utility.client.ClientRefuse;
import ru.nsu.netesov.lab5.utility.client.LoginToTheSystem;
import ru.nsu.netesov.lab5.utility.client.LogoutToTheSystem;
import ru.nsu.netesov.lab5.utility.server.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Objects;

public class Parser {
    private static final DocumentBuilderFactory company = DocumentBuilderFactory.newInstance();

    public Message parseCommand(String xml) {
        Message message = null;

        try {
            Document xmlInputMessage = company.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            String tag = xmlInputMessage.getDocumentElement().getTagName();
            if (tag.equals("message")) {
                String type = xmlInputMessage.getDocumentElement().getFirstChild().getTextContent();
                NodeList nodeList;
                String content;
                if(Objects.equals(type, "error")) {
                    nodeList = xmlInputMessage.getElementsByTagName("reason");
                    message = new ErrorMessage(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "success")) {
                    message = new SuccessMessage();
                }
                else if(Objects.equals(type, "login")) {
                    nodeList = xmlInputMessage.getElementsByTagName("name");
                    message = new LoginToTheSystem(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "connection")) {
                    nodeList = xmlInputMessage.getElementsByTagName("session");
                    message = new ServerConfirm(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "getlist")) {
                    nodeList = xmlInputMessage.getElementsByTagName("session");
                    message = new ListOfUsers(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "list")) {
                    LinkedList<String> users = new LinkedList<>();
                    nodeList = xmlInputMessage.getElementsByTagName("user");
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        users.add(nodeList.item(i).getTextContent());
                    }
                    message = new ServerList(users);
                }
                else if(Objects.equals(type, "logout")) {
                    nodeList = xmlInputMessage.getElementsByTagName("session");
                    message = new LogoutToTheSystem(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type,"client_message")) {
                    nodeList = xmlInputMessage.getElementsByTagName("content");
                    content = nodeList.item(0).getTextContent().replaceAll("\\\\n", "\\\n");
                    nodeList = xmlInputMessage.getElementsByTagName("session");
                    message = new ClientMessage(content, nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "server_message")) {
                    nodeList = xmlInputMessage.getElementsByTagName("content");
                    content = nodeList.item(0).getTextContent().replaceAll("\\\\n", "\\\n");
                    nodeList = xmlInputMessage.getElementsByTagName("name");
                    message = new ServerMessage(content, nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "userlogin")) {
                    nodeList = xmlInputMessage.getElementsByTagName("name");
                    message = new ServerNewUser(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "userlogout")) {
                    nodeList = xmlInputMessage.getElementsByTagName("name");
                    message = new ServerUserLogout(nodeList.item(0).getTextContent());
                }
                else if(Objects.equals(type, "refuse")) {
                    nodeList = xmlInputMessage.getElementsByTagName("refuse");
                    message = new ClientRefuse();
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
