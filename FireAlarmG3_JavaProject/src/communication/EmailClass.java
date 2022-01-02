/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

/**
 *
 * @author Eng_Hager
 */

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailClass {
    
    
	/**
	 * sendEmail method to send simple HTML email
	 * @param toEmail
	 * @param body
	 */

         
	public static void sendEmail(String toEmail, String body){
            
            final String fromEmail = "falarm256@gmail.com"; //requires valid gmail id
            final String password = "firealarm256"; // correct password for gmail id
            
            Properties props = new Properties();
            
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth;
            auth = new Authenticator() {
                //override the getPasswordAuthentication method
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            
            
            Session session = Session.getInstance(props, auth);
            String subject = "Fire Alarm";
            
            
            
            try
	    {
                //String from = "aliy.mahmoud00@gmail.com";
                MimeMessage msg = new MimeMessage(session);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");
                msg.setFrom(new InternetAddress(fromEmail, "Fire_System")); // sender email

                msg.setReplyTo(InternetAddress.parse(fromEmail, false)); 

                msg.setSubject(subject, "UTF-8");

                msg.setText(body, "UTF-8");

                msg.setSentDate(new Date());

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                System.out.println("Message is ready");
                Transport.send(msg);  

                System.out.println("EMail Sent Successfully!!");
	    }
                
	    catch (Exception e) {
                
                e.printStackTrace();
	    }
                
	}
    
}
