/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Properties;
//import javax.mail.Session;
//import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
//import sun.plugin2.message.transport.Transport;


public class SendMail {

    private String user = "rihab.haddad@esprit.tn";
    private String password = "rihabUNITEDCODERS17";

    public SendMail(String to, String sub, String msg) {
//        Properties prop = new Properties();
//        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        prop.put("mail.smtp.auth", true);
//        prop.put("mail.smtp.starttls.enable", true);
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", user);
        properties.setProperty("mail.smtp.password", password);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

                return new javax.mail.PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage msgg = new MimeMessage(session);
            msgg.setFrom(new InternetAddress(user));
            msgg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
            msgg.setSubject(sub);
            msgg.setText(msg);
            msgg.reply(true);

            Transport.send(msgg);
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
