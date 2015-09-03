package fr.keyconsulting.formation.service;

public class HelloService implements IHelloService {

	@Override
	public String sayHi(String name) {
		return "Hello " + name + "!";
	}

}
