package fr.keyconsulting.formation.service1;

import javax.jws.WebService;

@WebService
public interface IHelloService {

	public String sayHi(String name);

}