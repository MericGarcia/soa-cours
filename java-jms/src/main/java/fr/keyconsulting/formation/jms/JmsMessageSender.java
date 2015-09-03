package fr.keyconsulting.formation.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.Calcul;

@Service
public class JmsMessageSender {

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("jmsTemplateForListener")
	private JmsTemplate jmsTemplateForListener;
	
	@Autowired
	@Qualifier("jmsTemplateCalcul")
	private JmsTemplate jmsTemplateCalcul;

	public void send(final String messageText) {

		this.jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(messageText);
				return message;
			}
		});
	}
	
	public void send(final Calcul calcul) {

		this.jmsTemplateCalcul.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createObjectMessage(calcul);
				return message;
			}
		});
	}
	
	public void sendForListener(final String messageText) {

		this.jmsTemplateForListener.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(messageText);
				return message;
			}
		});
	}

}