package fr.keyconsulting.formation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.persistence.ORMPersistenceService;

public class CalculService implements ICalculService {

	private JmsServiceHelper service = new JmsServiceHelper();

	public List<Calcul> getAll() {
		List<Calcul> calculs = new ArrayList<>();
		Calcul next = service.nextCalcul();
		while(next != null){
			calculs.add(next);
			next = service.nextCalcul();
		}
		return calculs;
	}

	public void addCalcul(Calcul calc) {
		if (calc.getTime() == null) {
			calc.setTime(LocalDateTime.now());
		}
		service.send(calc);;
	}

}