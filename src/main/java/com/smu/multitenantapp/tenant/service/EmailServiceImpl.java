package com.smu.multitenantapp.tenant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService{

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final String SMTP_SERVER = "smtp.gmail.com";
    private final String USERNAME = "cs470.2020@gmail.com";
    private final String PASSWORD = "2020.cs470";

    private static String emailFrom = "cs470.2020@gmail.com";
    
	@Override
	public boolean emailAllManagers(List<String> emails) {

	    String emailTo = "";
	    //String emailToCc = "";
	    String emailSubject = "Claim Approval Pending";
	    String emailMsg = "A claim has been submitted for your approval!";
	    
		for (String email:emails) {
			emailTo = emailTo+email+",";
		}
		emailTo = emailTo.substring(0, emailTo.length()-1);
		
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);
        
        try {
    		
			// from
            msg.setFrom(new InternetAddress(emailFrom));

			// to 
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));

			// cc
            //msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailToCc, false));

			// subject
            msg.setSubject(emailSubject);
			
			// content 
            msg.setText(emailMsg);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            t.close();
            LOG.info("Email: Email successfully sent. "+t.getLastServerResponse());
            return true;

        } catch (MessagingException e) {
            LOG.error("Email: Error sending email: " +e.getMessage());;
            return false;
        }
		
	}

	@Override
	public boolean emailApprovingManager(String email) {
		ArrayList<String> emailList = new ArrayList<>();
		emailList.add(email);
		return emailAllManagers(emailList);
	}
	

}
