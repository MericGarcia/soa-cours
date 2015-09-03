package fr.keyconsulting.formation.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import fr.keyconsulting.formation.model.Calcul;

@Service
public class JmsMessageListener implements MessageListener {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	  public void onMessage(final Message message) {
		String text = null;
	    try {
	    	text = (String) jmsTemplate.getMessageConverter().fromMessage(message);
	    } catch (JMSException e) {
	      e.printStackTrace();
	    }
	    System.out.println("Received :" + text != null ? text : "Empty ?");
	  }

	} 