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

/**
 *
 * @author zanesmith
 */
public class emailClient
{
     static class fireEmail
    {

        public fireEmail()
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("zcsastock@gmail.com", "Password32");
                }
            });

            try
            {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("zcsastock@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("zane.smith1@yahoo.com"));
                message.setSubject("Testing Subject");
                message.setText("Dear Mail Crawler,"
                        + "\n\n testing email for test purposes!");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    
}