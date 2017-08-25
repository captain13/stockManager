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
public class xmlManager {

    String currentUsersHomeDir = System.getProperty("user.home");
    //String location = currentUsersHomeDir + File.separator + "Documents\\NetBeansProjects\\stockManager\\ResturantManagementSystem\\src\\system\\Settings.xml";
    String location = ".\\src\\system\\Settings.xml";
    File xmlSettings = new File(location);
    String resoultion;
    String tableCount;
    String email;
    String password;

    public final void xmlValidition() {
        if (xmlSettings.exists()) {
            getSettings();
            System.out.println("Settings file exists");
        } else {
            System.out.println("File not found");
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document document = docBuilder.newDocument();
                Element rootElement = document.createElement("settings");
                document.appendChild(rootElement);

                Element generalSettings = document.createElement("generalsettings");
                rootElement.appendChild(generalSettings);

                Element resolution = document.createElement("resolution");
                resolution.appendChild(document.createTextNode("Fullscreen"));
                generalSettings.appendChild(resolution);

                Element logo = document.createElement("logo");
                logo.appendChild(document.createTextNode("default"));
                generalSettings.appendChild(logo);

                Element tableNumber = document.createElement("tableNum");
                tableNumber.appendChild(document.createTextNode("15"));
                generalSettings.appendChild(tableNumber);

                Element emailAddress = document.createElement("emailAddress");
                emailAddress.appendChild(document.createTextNode("null"));
                generalSettings.appendChild(emailAddress);

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
            resoultion = document.getElementsByTagName("resolution").item(0).getTextContent();
            tableCount = document.getElementsByTagName("tableNum").item(0).getTextContent();
            email = document.getElementsByTagName("emailAddress").item(0).getTextContent();
            password = document.getElementsByTagName("emailPassword").item(0).getTextContent();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(xmlManager.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void updateSettings(String selectedItem, String selectedTable, String emailAddress) {
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

                if ("resolution".equals(node.getNodeName())) {
                    node.setTextContent(selectedItem);
                }

                if ("tableNum".equals(node.getNodeName())) {
                    node.setTextContent(selectedTable);
                }

                if ("emailAddress".equals(node.getNodeName())) {
                    node.setTextContent(emailAddress);
                }

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlSettings);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
        } catch (TransformerException ex) {
            Logger.getLogger(xmlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getSetting() {
        getResolution();
        getTableCount();
    }

    public String getResolution() {
        return resoultion;
    }

    public String getTableCount() {
        return tableCount;
    }

    public String getEmail() {
        getSettings();
        return email;
    }

    public String getEmailPassword() {
        getSettings();
        return password;
    }

}
