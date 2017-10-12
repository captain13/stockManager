/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrew
 */
public class settingsManager {

    String currentUsersHomeDir = System.getProperty("user.home");
    String location = System.getProperty("user.home") + "\\AppData\\Roaming\\ResturantManagement\\Settings.xml";
    File xmlSettings = new File(location);
    String logo;
    String resoultion;
    String tableCount;
    String email;
    String password;
    String color;
    String usernameDB;
    String passwordDB;

    public final void xmlValidition() {
        if (xmlSettings.exists()) {
            getSettings();
            System.out.println("Settings file exists");
        } else {
            System.out.println("File not found");
            try {
                new File(System.getProperty("user.home") + "\\AppData\\Roaming\\ResturantManagement\\").mkdirs();
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document document = docBuilder.newDocument();
                Element rootElement = document.createElement("settings");
                document.appendChild(rootElement);

                Element generalSettings = document.createElement("generalsettings");
                rootElement.appendChild(generalSettings);

                Element logo = document.createElement("logo");
                logo.appendChild(document.createTextNode(""));
                generalSettings.appendChild(logo);

                Element resolution = document.createElement("resolution");
                resolution.appendChild(document.createTextNode("Fullscreen"));
                generalSettings.appendChild(resolution);

                Element tableNumber = document.createElement("tableNum");
                tableNumber.appendChild(document.createTextNode("15"));
                generalSettings.appendChild(tableNumber);

                Element keyPad = document.createElement("enableKeyPad");
                keyPad.appendChild(document.createTextNode("0"));
                generalSettings.appendChild(keyPad);

                Element emailAddress = document.createElement("emailAddress");
                emailAddress.appendChild(document.createTextNode("null"));
                generalSettings.appendChild(emailAddress);

                Element emailPassword = document.createElement("emailPassword");
                emailPassword.appendChild(document.createTextNode("null"));
                generalSettings.appendChild(emailPassword);

                Element themeColor = document.createElement("themeColor");
                themeColor.appendChild(document.createTextNode("Blue"));
                generalSettings.appendChild(themeColor);

                Element databaseUsername = document.createElement("databaseUsername");
                databaseUsername.appendChild(document.createTextNode("null"));
                generalSettings.appendChild(databaseUsername);

                Element databasePassword = document.createElement("databasePassword");
                databasePassword.appendChild(document.createTextNode("null"));
                generalSettings.appendChild(databasePassword);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(xmlSettings);
                transformer.transform(source, result);
                System.out.println("File create");
            } catch (ParserConfigurationException | TransformerException pce) {
            }
        }
    }

    public void getSettings() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlSettings);
            NodeList nList = document.getElementsByTagName("generalsettings");
            logo = document.getElementsByTagName("logo").item(0).getTextContent();
            resoultion = document.getElementsByTagName("resolution").item(0).getTextContent();
            tableCount = document.getElementsByTagName("tableNum").item(0).getTextContent();
            email = document.getElementsByTagName("emailAddress").item(0).getTextContent();
            password = document.getElementsByTagName("emailPassword").item(0).getTextContent();
            color = document.getElementsByTagName("themeColor").item(0).getTextContent();
            usernameDB = document.getElementsByTagName("databaseUsername").item(0).getTextContent();
            passwordDB = document.getElementsByTagName("databasePassword").item(0).getTextContent();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(settingsManager.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void updateSettings(String image, String selectedItem, String selectedTable, String emailAddress,
            String emailPassword, String themeColor, String dbUsername, String dbPassword) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlSettings);

            Element rootElement = document.getDocumentElement();
            Node director = document.getElementsByTagName("generalsettings").item(0);

            rootElement.appendChild(director);
            NodeList list = director.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if ("logo".equals(node.getNodeName())) {
                    node.setTextContent(image);
                }

                if ("resolution".equals(node.getNodeName())) {
                    node.setTextContent(selectedItem);
                }

                if ("tableNum".equals(node.getNodeName())) {
                    node.setTextContent(selectedTable);
                }

                if ("emailAddress".equals(node.getNodeName())) {
                    node.setTextContent(emailAddress);
                }

                if ("emailPassword".equals(node.getNodeName())) {
                    node.setTextContent(emailPassword);
                }
                if ("themeColor".equals(node.getNodeName())) {
                    node.setTextContent(themeColor);
                }

                if ("databaseUsername".equals(node.getNodeName())) {
                    node.setTextContent(dbUsername);
                }

                if ("databasePassword".equals(node.getNodeName())) {
                    node.setTextContent(dbPassword);
                }

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlSettings);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
        } catch (TransformerException ex) {
            Logger.getLogger(settingsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLogo() {
        return logo;
    }

    public String getResolution() {
        return resoultion;
    }

    public String getTableCount() {
        getSettings();
        return tableCount;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailPassword() {
        return password;
    }

    public String getThemeColor() {
        return color;
    }

    public String getDatabaseUsername() {
        return usernameDB;
    }

    public String getDatabePassword() {
        return passwordDB;
    }

}
