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
		//implementer ici la récupération des calculs présents dans la file
		return calculs;
	}

	public void addCalcul(Calcul calc) {
		//implémenter ice l'envoi du calcul dans la file
	}

}