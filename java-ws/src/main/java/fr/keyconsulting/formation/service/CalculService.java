package fr.keyconsulting.formation.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.persistence.ORMPersistenceService;

public class CalculService implements ICalculService {

	private PersistenceService persistenceService = new ORMPersistenceService();

	public List<Calcul> getAll() {
		return persistenceService.load();
	}

	public void addCalcul(Calcul calc) {
		if (calc.getTime() == null) {
			calc.setTime(LocalDateTime.now());
		}
		persistenceService.persist(calc);
	}

}