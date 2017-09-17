/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author zanesmith
 */
public class emailClient {

    settingsManager settings = new settingsManager();
    receiptHandler newHandler = new receiptHandler();
    String emailAddress;
    String password;

    public emailClient() {
        settings.getSettings();
        emailAddress = settings.getEmail();
        password = settings.getEmailPassword();
    }

    public void sendEmail(String recipientAddress, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAddress, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAddress));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientAddress));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Email Sent");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Email Failed to Send");
            throw new RuntimeException(e);
        }
    }

    public void emailTemplate(String supplier, String item, double qty) {
        String template = "Dear " + supplier + "\n" + "\n"
                + "We would like to order " + item + "(s) with the quantity of " + qty + "(g) \n" + "\n"
                + "Regards \n"
                + "Zcsas Development Team";
        System.out.println(template);
        sendEmail("swabe@live.co.za", "Reorder", template);
    }

    public void emailTemplate(String email, double total) {
        newHandler.printReceipt(total);
        String text = newHandler.getReceipt();
        sendEmail(email, "Receipt", text);
    }
}
