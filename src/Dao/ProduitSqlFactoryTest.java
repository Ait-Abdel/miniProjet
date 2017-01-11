package Dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import metier.I_Produit;
import metier.Produit;

public class ProduitSqlFactoryTest {
	private ProduitSqlFactory produitSqlFactory;

	
	
	@Before
	public void instanceProduitSqlFactory() {
		produitSqlFactory = new ProduitSqlFactory();
	}
	
	@Test
	public void testConstructeurProduitSqlFactory() {
		assertNotNull("créer produitSqlFactory ", produitSqlFactory);
	}
	
	@Test
	public void ajouterUnProduit(){
		String nom = "Kit Kat V2";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		assertTrue("Ajouter un produit", produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock));
	}
	
	@Test
	public void ajouterDeuxProduitsIdentiques(){
		String nom = "Kit Kat";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		assertTrue("Ajout du premier produit", produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock));
		assertFalse("Ajout du deuxieme produit", produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock));

	}
	
	
	@Test
	public void supprimerUnProduit() {
		String nom = "Mars";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		assertTrue("Supprimer un produit",produitSqlFactory.deleteProduit(nom));

	}
	
	@Test
	public void supprimerDeuxProduitsIdentiques() {
		String nom = "Terre";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		assertTrue("Supprimer premier produit",produitSqlFactory.deleteProduit(nom));
		assertFalse("Supprimer deuxieme produit",produitSqlFactory.deleteProduit(nom));

	}
	
	@Test 
	public void supprimerTousLesProduit_AvecDesProduits(){
		String nom = "Mars", nom2="Venus", nom3 ="Saturne";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		produitSqlFactory.createProduit(nom2, prixUnitaireHT, quantiteStock);
		produitSqlFactory.createProduit(nom3, prixUnitaireHT, quantiteStock);
		
		assertTrue("Supprimer tous les produits", produitSqlFactory.deleteAll());
		
	}
	@Test
	public void afficherTousLesProduits_AvecDesProduits(){
		produitSqlFactory.deleteAll();
		String nom = "Velo", nom2="Ski", nom3 ="Luge";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		produitSqlFactory.createProduit(nom2, prixUnitaireHT, quantiteStock);
		produitSqlFactory.createProduit(nom3, prixUnitaireHT, quantiteStock);
		
		
		List<I_Produit>lesProduits = new ArrayList<>();
		lesProduits.add(new Produit(nom, prixUnitaireHT, quantiteStock));
		lesProduits.add(new Produit(nom2, prixUnitaireHT, quantiteStock));
		lesProduits.add(new Produit(nom3, prixUnitaireHT, quantiteStock));
		
		assertEquals("Afficher tous les produits", produitSqlFactory.getToutLesProduits().toString(), lesProduits.toString());
	}
	
	@Test
	public void afficherTousLesProduits_SansProduits(){
		produitSqlFactory.deleteAll();
		List<I_Produit>lesProduits = new ArrayList<>();
		
		assertEquals("Afficher tous les produits", produitSqlFactory.getToutLesProduits().toString(), lesProduits.toString());
	}
	
	@Test
	public void afficherUnProduits(){
		
		String nom = "Montre";
		double prixUnitaireHT = 1;
		int quantiteStock = 2;
		//produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		I_Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
		
		assertEquals("Afficher tous les produits",produit.toString(), produitSqlFactory.getProduit(nom).toString());
	}
	
	
	@Test
	public void enleverUneQteProduit_leProduitExist(){
		String nom = "Crayon à mine";
		double prixUnitaireHT = 3;
		int quantiteStock = 22;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertTrue("Enlever une quantité a un produit existant",produitSqlFactory.enleverQuantiteProduit(nom, 2));
		
	}
	
	@Test
	public void enleverUneQteSupProduit_leProduitExist(){
		String nom = "Bouteille de lait";
		double prixUnitaireHT = 3;
		int quantiteStock = 1;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertFalse("Enlever une quantité a un produit existant",produitSqlFactory.enleverQuantiteProduit(nom, 2));
		
	}
	
	@Test
	public void ajouterUneQteProduit_leProduitExist(){
		String nom = "Bouteille deau mineral";
		double prixUnitaireHT = 2;
		int quantiteStock = 1;
		produitSqlFactory.createProduit(nom, prixUnitaireHT, quantiteStock);
		
		assertTrue("Ajouter une quantité a un produit existant",produitSqlFactory.ajouterQuantiteProduit(nom, 2));
		
	}
	
	
	
	
	

}
