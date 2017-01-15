package application;

import metier.I_Catalogue;

public class ControleurAchatVente {
	private I_Catalogue cat;
	
	public ControleurAchatVente(I_Catalogue p_cat){
		this.cat = p_cat;
	}
	
	
	public boolean achatProduit(String nomProduit, int qtAcheter){
		return cat.acheterStock(nomProduit,qtAcheter);
	} 
	
	public boolean venteProduit(String nomProduit, int qtVendu){
		return cat.vendreStock(nomProduit,qtVendu);
	} 
	
	
	
	
	
	

}
