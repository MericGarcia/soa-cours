package fr.keyconsulting.formation.service2;

import fr.keyconsulting.formation.service2.IHelloService2;

public class HelloService2 implements IHelloService2 {

	@Override
	public String sayHi(String name) {
		return "Hello 2 " + name + "!";
	}

}
