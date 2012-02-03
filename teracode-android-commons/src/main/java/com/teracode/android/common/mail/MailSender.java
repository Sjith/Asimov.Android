package com.teracode.android.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.Log;

import com.teracode.android.common.util.PropertiesUtil;

/**
 * @author Luciano Rey
 */
public class MailSender {
	
	private static final String TAG = MailSender.class.getSimpleName();
	
	private static final int MAIL_PORT = PropertiesUtil.getIntegerProperty("mail.port", 2525);
	private static final String MAIL_USER = PropertiesUtil.getStringProperty("mail.user"); //"sendersmtp@teracode.com";
	private static final String MAIL_PASSWORD = PropertiesUtil.getStringProperty("mail.password"); //"t3r4c0d3";
	private static final String MAIL_HOST = PropertiesUtil.getStringProperty("mail.host", "smtp.com");
	private static final String MAIL_DEFAULT_FROM = PropertiesUtil.getStringProperty("mail.default.from");
	private static final String MAIL_DEFAULT_TO = PropertiesUtil.getStringProperty("mail.default.to");
	private static final String SMTP = PropertiesUtil.getStringProperty("mail.smtp", "smtp");
	
	private static MailSender INSTANCE = null;
	
	private Session session = Session.getDefaultInstance(new Properties(), null);
	
	public static MailSender get() {
		if (INSTANCE == null) {
			INSTANCE = new MailSender();
		}
		return INSTANCE;
	}
	
	/**
	 * @param subject
	 * @param body
	 * @param sender
	 * @param recipient
	 * @throws MailException
	 */
	public void sendMail(String subject, String body, String sender, String recipient) throws MailException {
		
		Transport transport = null;
		try {
			Message message = this.makeMessage(subject, body, sender, recipient);
			transport = this.makeTransport();
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			throw new MailException(e);
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					Log.e(TAG, "", e);
				}
			}
		}
	}
	
	/**
	 * @param subject
	 * @param body
	 * @throws MailException
	 */
	public void sendMail(String subject, String body) throws MailException {
		sendMail(subject, body, MAIL_DEFAULT_FROM, MAIL_DEFAULT_TO);
	}
	
	private Message makeMessage(String subject, String body, String sender, String recipient) throws AddressException,
			MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sender));
		message.setSubject(subject);
		message.setText(body);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.saveChanges();
		
		return message;
	}
	
	private Transport makeTransport() throws MessagingException {
		Transport transport = session.getTransport(SMTP);
		transport.connect(MAIL_HOST, MAIL_PORT, MAIL_USER, MAIL_PASSWORD);
		return transport;
	}
}
