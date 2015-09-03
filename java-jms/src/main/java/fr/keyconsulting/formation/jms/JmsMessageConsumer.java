package fr.keyconsulting.formation.jms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.Calcul;

@Service
public class JmsMessageConsumer {

	private static final int TIMEOUT_MESSAGE_RECEPTION = 50;
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("jmsTemplateCalcul")
	private JmsTemplate jmsTemplateCalcul;
	
	@PostConstruct
	private void init(){
		jmsTemplate.setReceiveTimeout(TIMEOUT_MESSAGE_RECEPTION);
		jmsTemplateCalcul.setReceiveTimeout(TIMEOUT_MESSAGE_RECEPTION);
	}

	public String getFollowingText() {
		String message = (String) jmsTemplate.receiveAndConvert();
		return message;
	}
	
	public Calcul getFollowingCalcul() {
		Calcul message = (Calcul) jmsTemplateCalcul.receiveAndConvert();
		return message;
	}

}