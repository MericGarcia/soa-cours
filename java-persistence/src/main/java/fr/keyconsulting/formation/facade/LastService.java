package fr.keyconsulting.formation.facade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.persistence.FilePersistenceService;
import fr.keyconsulting.formation.persistence.ORMPersistenceService;
import fr.keyconsulting.formation.service.PersistenceService;

public class LastService implements ILastService{
	
	PersistenceService filePersistence = new FilePersistenceService();
	PersistenceService ormPersitence = new ORMPersistenceService();

	@Override
	public Calcul getLastCalcul() {
		return null;
	}

	@Override
	public void persist(Calcul calcul) {
		ormPersitence.persist(calcul);
	}

	
	
}
