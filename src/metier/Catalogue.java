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
	private I_ProduitDAO produitDAO;
	private NumberFormat nf = NumberFormat.getCurrencyInstance();

	public Catalogue() {
		ProduitFactory factory = new ProduitFactory();
		produitDAO = factory.createProduitDAO();
		try {
			lesProduits = produitDAO.getToutLesProduits();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	public Catalogue(List<I_Produit> produits) {
		produitDAO = new ProduitDAOPourTest();
		lesProduits = produits;
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		boolean produitCree = false;
		if (produit != null && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) {
			if (leProduitExiste(produit.getNom()) == false) {
				String nom = produit.getNom();
				double prix = produit.getPrixUnitaireHT();
				int qte = produit.getQuantite();
				try {
					produitCree = produitDAO.createProduit(nom, prix, qte);
				} catch (DAOException e) {
					e.printStackTrace();
				}
				if (produitCree)
					lesProduits.add(produit);
			}
		}

		return produitCree;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean produitCree = false;

		if (leProduitExiste(nom) == false && prix > 0 && qte >= 0) {
			Produit unProduit = new Produit(nom, (float) prix, qte);
			try {
				produitCree = produitDAO.createProduit(nom, prix, qte);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if (produitCree)
				lesProduits.add(unProduit);
		}

		return produitCree;
	}

	@Override
	public int addProduits(List<I_Produit> listProduits) {
		int nbProduitAjoutee = 0;
		if (listProduits != null) {
			for (int i = 0; i < listProduits.size(); i++) {
				if (leProduitExiste(listProduits.get(i).getNom()) == false && listProduits.get(i).getQuantite() >= 0
						&& listProduits.get(i).getPrixUnitaireHT() > 0) {
					String nom = listProduits.get(i).getNom();
					double prix = listProduits.get(i).getPrixUnitaireHT();
					int qte = listProduits.get(i).getQuantite();
					try {
						if (produitDAO.createProduit(nom, prix, qte))
							lesProduits.add(listProduits.get(i));
					} catch (DAOException e) {
						e.printStackTrace();
					}
					nbProduitAjoutee++;
				}
			}
		}
		return nbProduitAjoutee;
	}

	@Override
	public boolean removeProduit(String nom) {
		boolean produitSupprime = false;
		if (leProduitExiste(nom) && nom != null) {
			try {
				produitSupprime = produitDAO.deleteProduit(nom);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if (produitSupprime)
				lesProduits.remove(get(nom));
		}
		return produitSupprime;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean stockAjoute = false;
		if (leProduitExiste(nomProduit)) {
			try {
				stockAjoute = produitDAO.ajouterQuantiteProduit(nomProduit, qteAchetee);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if(stockAjoute)
				stockAjoute = get(nomProduit).ajouter(qteAchetee);
		}
		return stockAjoute;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean leStockEstVendu = false;
		if (leProduitExiste(nomProduit)) {
			try {
				leStockEstVendu = produitDAO.enleverQuantiteProduit(nomProduit, qteVendue);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			if(leStockEstVendu)
				leStockEstVendu = get(nomProduit).enlever(qteVendue);
		}
		return leStockEstVendu;
	}

	@Override
	public String[] getNomProduits() {
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
		double montantTotalTTC = 0;
		int i = 0;
		while (i < lesProduits.size()) {
			montantTotalTTC += lesProduits.get(i).getPrixStockTTC();
			i++;
		}
		return (double) Math.round(montantTotalTTC * 100) / 100;
	}

	@Override
	public void clear() {
		lesProduits.clear();
		try {
			produitDAO.deleteAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
	private boolean leProduitExiste(String nomP) {
		boolean produitExist = false;
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

	private I_Produit get(String nomP) {
		I_Produit produitNom = null;
		boolean check = false;
		int i = 0;
		if (leProduitExiste(nomP) && nomP != null) {

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

		while (i < lesProduits.size()) {
			toStringCat += lesProduits.get(i).toString() + "\n";
			i++;
		}
		toStringCat += "\nMontant total TTC du stock : " + nf.format(getMontantTotalTTC());
		return toStringCat;
	}

	@Override
	public void fermerAccesAuDonnees() {
		produitDAO.fermerAccesAuDonnees();
	}

}
