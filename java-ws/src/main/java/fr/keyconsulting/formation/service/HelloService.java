package fr.keyconsulting.formation.service;

public class HelloService implements IHelloService{

	public String sayHi(String name) {
		return "Hello " + name;
	}

}