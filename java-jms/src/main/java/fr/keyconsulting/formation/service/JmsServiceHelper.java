package fr.keyconsulting.formation.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.keyconsulting.formation.jms.JmsMessageConsumer;
import fr.keyconsulting.formation.jms.JmsMessageSender;
import fr.keyconsulting.formation.model.Calcul;


public class JmsServiceHelper {
	
	JmsMessageSender jmsMessageSender;
	JmsMessageConsumer jmsMessageConsumer;
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-context-jms.xml");
	
	
	public JmsServiceHelper(){
	    jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");		
	    jmsMessageConsumer = (JmsMessageConsumer)ctx.getBean("jmsMessageConsumer");				
	}
	     
	public void send(String text){
		jmsMessageSender.send(text);
	}
	
	public void send(Calcul calcul){
		jmsMessageSender.send(calcul);
	}
	
	public void sendForListener(String text){
		jmsMessageSender.sendForListener(text);
	}
	
	public String next(){
		return jmsMessageConsumer.getFollowingText();
	}
	
	public Calcul nextCalcul(){
		return jmsMessageConsumer.getFollowingCalcul();
	}
}