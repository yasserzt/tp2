/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author i__t__s
 */
public class Mailing {
  public static void sendMail (String recipient, String content)
  {
    System.out.println("preparer envoi");
    Properties properties = new Properties ();
    properties.put ("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    String myaccountemail = "farm1items@gmail.com";
    String password = "farmitems135";
    Session session = Session.getDefaultInstance(properties, new Authenticator(){

        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
          return new javax.mail.PasswordAuthentication(myaccountemail, password);
        }

    });
         Message message = prepareMessage (session, myaccountemail, recipient, content);
        try {
            Transport.send (message);
        } catch (MessagingException ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("email envoyé");
         
}

         
            public static Message prepareMessage (Session session, String myaccountemail, String recipient, String content)
            {
             try {
                 String actualDate="";
                 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                 LocalDateTime now = LocalDateTime.now();  
                 actualDate=dtf.format(now).toString();
                 Message message = new MimeMessage(session);
                 message.setFrom ( new InternetAddress(myaccountemail));
                 message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                 message.setSubject("Notification Bookstore EWA");
                 message.setText("Ceci est un mail automatique envoyé le "+actualDate
                         + " Bookstore EWA vous informe que "+content);
                 return message;
             } catch (MessagingException ex) {
                 Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
             }
               return null;
            }
   
   
   
   
   
    
    
}
