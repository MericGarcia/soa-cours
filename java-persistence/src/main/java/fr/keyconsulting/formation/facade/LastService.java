package fr.keyconsulting.formation.facade;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.persistence.FilePersistenceService;
import fr.keyconsulting.formation.persistence.ORMPersistenceService;
import fr.keyconsulting.formation.service.PersistenceService;

public class LastService implements ILastService{
	
	PersistenceService filePersistence = new FilePersistenceService();
	PersistenceService ormPersitence = new ORMPersistenceService();

	@Override
	public Calcul getLastCalcul() {
		// get the last persistence from the two service
		return null;
	}

	
	
}
