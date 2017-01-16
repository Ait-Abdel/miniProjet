package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Dao.*;
import metier.I_Produit;
import metier.Produit;

public class ProduitSqlFactoryTest {
	private I_ProduitDAO produitDAO;

	@Before
	public void setUp() throws Exception {
		produitDAO.deleteAll();
		//produitSqlFactory.fermerAccesAuDonnees();
		ProduitFactory factory = new ProduitFactory();
		produitDAO = factory.createProduitDAO();
	}
	
	
	@Before
	public void instanceProduitSqlFactory() {
		ProduitFactory factory = new ProduitFactory();
		produitDAO = factory.createProduitDAO();
	}
	
	@Test
	public void testConstructeurProduitSqlFactory() {
		assertNotNull("cr�er produitSqlFactory ", produitDAO);
	}
	
	@Test
	public void ajouterUnProduit(){
		String nom = "Kit Kat V2";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		assertTrue("Ajouter un produit", produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock));
	}
	
	@Test
	public void ajouterDeuxProduitsIdentiques(){
		String nom = "Kit Kat";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		assertTrue("Ajout du premier produit", produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock));
		assertFalse("Ajout du deuxieme produit", produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock));

	}
	
	
	@Test
	public void supprimerUnProduit() {
		String nom = "Mars";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		assertTrue("Supprimer un produit",produitDAO.deleteProduit(nom));
		produitDAO.fermerAccesAuDonnees();

	}
	
	@Test
	public void supprimerDeuxProduitsIdentiques() {
		String nom = "Terre";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		assertTrue("Supprimer premier produit",produitDAO.deleteProduit(nom));
		assertFalse("Supprimer deuxieme produit",produitDAO.deleteProduit(nom));

	}
	
	@Test 
	public void supprimerTousLesProduit_AvecDesProduits(){
		String nom = "Mars", nom2="Venus", nom3 ="Saturne";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		produitDAO.createProduit(nom2, prixUnitaireHT, quantiteStock);
		produitDAO.createProduit(nom3, prixUnitaireHT, quantiteStock);
		
		assertTrue("Supprimer tous les produits", produitDAO.deleteAll());
		
	}
	@Test
	public void afficherTousLesProduits_AvecDesProduits(){
		String nom = "Velo", nom2="Ski", nom3 ="Luge";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		produitDAO.createProduit(nom2, prixUnitaireHT, quantiteStock);
		produitDAO.createProduit(nom3, prixUnitaireHT, quantiteStock);
		
		
		List<I_Produit>lesProduits = new ArrayList<>();
		lesProduits.add(new Produit(nom, prixUnitaireHT, quantiteStock));
		lesProduits.add(new Produit(nom2, prixUnitaireHT, quantiteStock));
		lesProduits.add(new Produit(nom3, prixUnitaireHT, quantiteStock));
		
		assertEquals("Afficher tous les produits", produitDAO.getToutLesProduits().toString(), lesProduits.toString());
	}
	
	@Test
	public void afficherTousLesProduits_SansProduits(){
		List<I_Produit>lesProduits = new ArrayList<>();
		
		assertEquals("Afficher tous les produits", produitDAO.getToutLesProduits().toString(), lesProduits.toString());
	}
	
	@Test
	public void afficherUnProduits(){
		
		String nom = "Montre";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		I_Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
		
		assertEquals("Afficher tous les produits",produit.toString(), produitDAO.getProduit(nom).toString());
	}
	
	
	@Test
	public void enleverUneQteProduit_leProduitExist(){
		String nom = "Crayon � mine";
		double prixUnitaireHT = 3;
		int quantiteStock = 22;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertTrue("Enlever une quantit� a un produit existant",produitDAO.enleverQuantiteProduit(nom, 2));
		
	}
	
	@Test
	public void enleverUneQteSupProduit_leProduitExist(){
		String nom = "Bouteille de lait";
		double prixUnitaireHT = 3;
		int quantiteStock = 1;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertFalse("Enlever une quantit� a un produit existant",produitDAO.enleverQuantiteProduit(nom, 2));
		
	}
	
	@Test
	public void ajouterUneQteProduit_leProduitExist(){
		String nom = "Bouteille deau mineral";
		double prixUnitaireHT = 2;
		int quantiteStock = 1;
		produitDAO.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertTrue("Ajouter une quantit� a un produit existant",produitDAO.ajouterQuantiteProduit(nom, 2));
		
	}
	
	
	
	
	

}
