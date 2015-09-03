package fr.keyconsulting.formation.facade;

import fr.keyconsulting.formation.model.Calcul;

public interface ILastService {

	Calcul getLastCalcul();

	void persist(Calcul calcul);
	
}
