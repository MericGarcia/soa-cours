package fr.keyconsulting.formation.service1;

import fr.keyconsulting.formation.service1.IHelloService;

public class HelloService implements IHelloService {

	@Override
	public String sayHi(String name) {
		return "Hello " + name + "!";
	}

}
