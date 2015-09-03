package fr.keyconsulting.formation.jms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.Calcul;

@Service
public class JmsMessageConsumer {

	private static final int TIMEOUT_MESSAGE_RECEPTION = 50;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@PostConstruct
	private void init(){
		jmsTemplate.setReceiveTimeout(TIMEOUT_MESSAGE_RECEPTION);
	}

	public String getFollowingText() {
		String message = (String) jmsTemplate.receiveAndConvert();
		return message;
	}

}