package application;

import Dao.*;
import metier.*;

public class ControleurNouveauSuppression {
	private I_Catalogue cat;
	private I_ProduitDAO produitFactory;
	
	
	public ControleurNouveauSuppression(I_Catalogue cat) {
		this.cat = cat;
//		produitFactory = new ProduitSqlFactory();
	}

	public String[] demandeSuppression(){
		return cat.getNomProduits(); 
	}
	
	public boolean supprimerProduit(String nomP){
		return cat.removeProduit(nomP);
	}
	
	public boolean ajouterProduit(String nom, double prix, int qte) {
		//return produitFactory.createProduit(nom, prix, qte);
		return cat.addProduit(nom, prix, qte);
	}
}
