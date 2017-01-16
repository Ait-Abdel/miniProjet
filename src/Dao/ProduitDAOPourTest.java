package Dao;

import java.util.List;

import metier.I_Produit;

public class ProduitDAOPourTest implements I_ProduitDAO {

	@Override
	public List<I_Produit> getToutLesProduits() {
		return null;
	}

	@Override
	public boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock) {
		return true;
	}

	@Override
	public boolean deleteProduit(String nom) {
		return true;
	}

	@Override
	public boolean enleverQuantiteProduit(String nom, int quantiteStock) {
		return true;
	}

	@Override
	public boolean ajouterQuantiteProduit(String nom, int quantiteStock) {
		return true;
	}

	@Override
	public boolean deleteAll() {
		return true;
	}

	@Override
	public I_Produit getProduit(String nom) {
		return null;
	}

	@Override
	public void fermerAccesAuDonnees() {

	}

}
