package com.example.auction.services;

import com.example.auction.entities.Lot;
import com.example.auction.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
    public JavaMailSender emailSender;
	
	public void send(String mailTo, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
	}
	
	public void sendGreetingMessage(User u) {
		send(u.getEmail(), "Успешная регистрация", "Здравствуйте, " + u.getLogin() + "! Спасибо за регистрацию. Вы можете начать пользоваться нашим сервисом прямо сейчас.");
	}

	public void sendBuyInformation(User u, Lot t) {
		send(u.getEmail(), "Спасибо за покупку", "Здравствуйте! Вы совершили покупку лота: "+t.getName()+" по цене: "+t.getCost() + " рублей");
	}
}
