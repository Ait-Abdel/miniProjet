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
	public List<I_Produit> getToutLesProduits() throws DAOException {
		List<I_Produit> i_Produits;
		try {
			i_Produits = produitDAO_XML.lireTous(); 
		} catch (Exception e) {
			throw new DAOException("Erreur lors de l'affichage de tous les produits");
		}
		return i_Produits;
		
	}

	@Override
	public boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock) throws DAOException {
		boolean produitCree = false;
		try {
			Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
			if(produitDAO_XML.lire(nom) == null)
				produitCree = produitDAO_XML.creer(produit);
		} catch (Exception e) {
			throw new DAOException("Erreur lors de l'ajout d'un produit");
		}
		return produitCree;
	}

	@Override
	public boolean deleteProduit(String nom) throws DAOException {
		 boolean estSupprimer = false;
		try {
			I_Produit produit = produitDAO_XML.lire(nom);
			 
			estSupprimer = produitDAO_XML.supprimer(produit);
		} catch (Exception e) {
			throw new DAOException("Erreur lors de la suppression d'un produit");
		}
		return estSupprimer;
	}

	@Override
	public boolean enleverQuantiteProduit(String nom, int quantiteStock) throws DAOException {
		boolean qteEnlevee = false;
		try {
			I_Produit produit = produitDAO_XML.lire(nom);
			if (produit.enlever(quantiteStock))
				qteEnlevee = produitDAO_XML.maj(produit);
		} catch (Exception e) {
			throw new DAOException("Erreur lors de la suppression d'une quantité d'un produit");
		}
		
		return qteEnlevee;
	}

	@Override
	public boolean ajouterQuantiteProduit(String nom, int quantiteStock) throws DAOException {
		boolean qteAjoutee = false;
		try {
			I_Produit produit = produitDAO_XML.lire(nom);
			if (produit.ajouter(quantiteStock))
				qteAjoutee = produitDAO_XML.maj(produit);
		} catch (Exception e) {
			throw new DAOException("Erreur lors de l'ajout d'une quantité d'un produit");
		}
		
		return qteAjoutee;
	}

	@Override
	public boolean deleteAll() throws DAOException {
		boolean supprimer = true;
		try {
			List<I_Produit> listProduit = getToutLesProduits();
					for (I_Produit i_Produit : listProduit) {
						supprimer = produitDAO_XML.supprimer(i_Produit);
					}
					listProduit.clear();
		} catch (DAOException e) {
			throw new DAOException("Erreur lors de la suppression de tous les produits");
		}	
				
		return supprimer;
	}

	@Override
	public I_Produit getProduit(String nom) throws DAOException {
		
		I_Produit lireProduit;
		try {
			lireProduit = produitDAO_XML.lire(nom);
		} catch (Exception e) {
			throw new DAOException("Erreur lors de l'affichage d'un produit");
		}
		return lireProduit;
	}

	@Override
	public void fermerAccesAuDonnees() {

	}

}
