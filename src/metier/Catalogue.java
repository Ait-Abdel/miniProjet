package metier;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Dao.*;

public class Catalogue implements I_Catalogue {

	private List<I_Produit> lesProduits = null;
	private ProduitFactory produitFactory;
	private NumberFormat nf = NumberFormat.getCurrencyInstance();

	public Catalogue() {
		
		produitFactory = new ProduitSqlFactory();
		lesProduits =  produitFactory.getToutLesProduits();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		// TODO Auto-generated method stub
		boolean produitCree = false;
		if (produit != null && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) {
			if (existe(produit.getNom()) == false) {
				lesProduits.add(produit);
				produitCree = true;
			}
		}

		return produitCree;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean produitCree = false;

		if (existe(nom) == false && prix > 0 && qte >= 0) {
			Produit unProduit = new Produit(nom, (float) prix, qte);
			produitCree = produitFactory.createProduit(nom, prix, qte);
			//lesProduits.add(unProduit);
		}

		return produitCree;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		// TODO Auto-generated method stub
		int j = 0;
		if (l != null) {
			for (int i = 0; i < l.size(); i++) {
				if (existe(l.get(i).getNom()) == false && l.get(i).getQuantite() >= 0
						&& l.get(i).getPrixUnitaireHT() > 0) {
					lesProduits.add(l.get(i));
					j++;
				}
			}
		}
		return j;
	}

	@Override
	public boolean removeProduit(String nom) {
		boolean produitSupprime = false;
		if (existe(nom) && nom != null) {
			produitSupprime = produitFactory.deleteProduit(nom);
		}
		return produitSupprime;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean stockAjoute = false;
		if (existe(nomProduit)) {
			stockAjoute = produitFactory.ajouterQuantiteProduit(nomProduit, qteAchetee);
//					get(nomProduit).ajouter(qteAchetee);
		}
		return stockAjoute;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean leStockEstVendu = false;
		if (existe(nomProduit)) {
			leStockEstVendu = produitFactory.enleverQuantiteProduit(nomProduit, qteVendue);
//			 = get(nomProduit).enlever(qteVendue);
		}
		return leStockEstVendu;
	}

	@Override
	public String[] getNomProduits() {
		lesProduits.clear();
		lesProduits	 = produitFactory.getToutLesProduits();
		String tableauCaractere[] = new String[lesProduits.size()];
		int i = 0;
		while (i < lesProduits.size()) {
			tableauCaractere[i] = lesProduits.get(i).getNom();
			i++;
		}
		Arrays.sort(tableauCaractere);
		return tableauCaractere;
	}

	@Override
	public double getMontantTotalTTC() {
		// TODO Auto-generated method stub
		double montantTotalTTC = 0;
		int i = 0;
		lesProduits.clear();
		lesProduits	 = produitFactory.getToutLesProduits();
		while (i < lesProduits.size()) {
			montantTotalTTC += lesProduits.get(i).getPrixStockTTC();
			i++;
		}
		return (double) Math.round(montantTotalTTC * 100) / 100;
		// return montantTotalTTC;
	}

	@Override
	public void clear() {
		lesProduits.clear();
		produitFactory.deleteAll();

	}
	/*
	 * Vrai si : le produit existe dans la liste Faux si : le produi n'existe
	 * pas
	 * 
	 */

	private boolean existe(String nomP) {
		// A Modifier /!\---------------------------------------------------/!\
		
		boolean produitExist = false;
		lesProduits.clear();
		lesProduits	 = produitFactory.getToutLesProduits();
		if (lesProduits != null && nomP != null) {
			nomP = nomP.replaceAll("[\\t]", " ").trim();
			int i = 0;
			int size = lesProduits.size();
			I_Produit cursor;
			while (i < size && produitExist != true) {
				cursor = lesProduits.get(i);
				if (cursor.getNom().equals(nomP)) {
					produitExist = true;
				}
				i++;
			}
		}
		return produitExist;
	}

	/*
	 * @Override public String toString() { return "Catalogue [lesProduits=" +
	 * lesProduits + "]"; }
	 */

	private I_Produit get(String nomP) {
		lesProduits.clear();
		lesProduits	 = produitFactory.getToutLesProduits();
		I_Produit produitNom = null;
		boolean check = false;
		int i = 0;
		if (existe(nomP) && nomP != null) {

			while (check == false) {
				if (lesProduits.get(i).getNom() == nomP) {
					check = true;
					produitNom = lesProduits.get(i);
				}
				i++;
			}

		}
		return produitNom;
	}

	@Override
	public String toString() {
		String toStringCat = "";
		int i = 0;
		List<I_Produit> lesProduitsBdd;
		lesProduitsBdd = produitFactory.getToutLesProduits();
		
		while (i < lesProduitsBdd.size()) {
			toStringCat += lesProduitsBdd.get(i).toString() + "\n";
			i++;
		}
		toStringCat += "\nMontant total TTC du stock : " + nf.format(getMontantTotalTTC());
		return toStringCat;
	}
	


}
