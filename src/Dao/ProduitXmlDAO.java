package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class ProduitXmlDAO implements I_ProduitDAO {
	private ProduitDAO_XML produitDAO_XML;

	public ProduitXmlDAO() {
		produitDAO_XML = new ProduitDAO_XML();
	}

	@Override
	public List<I_Produit> getToutLesProduits() {
		return produitDAO_XML.lireTous();
	}

	@Override
	public boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock) {
		boolean produitCree = false;
		Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
		if(produitDAO_XML.lire(nom) == null)
			produitCree = produitDAO_XML.creer(produit);
		return produitCree;
	}

	@Override
	public boolean deleteProduit(String nom) {
		 I_Produit produit = produitDAO_XML.lire(nom);
		return produitDAO_XML.supprimer(produit);
	}

	@Override
	public boolean enleverQuantiteProduit(String nom, int quantiteStock) {
		boolean qteEnlevee = false;
		I_Produit produit = produitDAO_XML.lire(nom);
		if (produit.enlever(quantiteStock))
			qteEnlevee = produitDAO_XML.maj(produit);
		return qteEnlevee;
	}

	@Override
	public boolean ajouterQuantiteProduit(String nom, int quantiteStock) {
		boolean qteAjoutee = false;
		I_Produit produit = produitDAO_XML.lire(nom);
		if (produit.ajouter(quantiteStock))
			qteAjoutee = produitDAO_XML.maj(produit);
		return qteAjoutee;
	}

	@Override
	public boolean deleteAll() {
		boolean supprimer = true;
		List<I_Produit> listProduit = getToutLesProduits();
				for (I_Produit i_Produit : listProduit) {
					supprimer = produitDAO_XML.supprimer(i_Produit);
				}
				listProduit.clear();		
		return supprimer;
	}

	@Override
	public I_Produit getProduit(String nom) {
		return produitDAO_XML.lire(nom);
	}

	@Override
	public void fermerAccesAuDonnees() {

	}

}
