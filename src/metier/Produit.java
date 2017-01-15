package metier;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Produit implements I_Produit{
		private int quantiteStock;
		private String nom;
		private double prixUnitaireHT;
		private static float tauxTVA = (float) 0.2;
		
		private NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		
	
		

	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
			this.nom = nom.trim().replaceAll("\t", " ");
			this.prixUnitaireHT = prixUnitaireHT;
			this.quantiteStock = quantiteStock;
		}

	@Override
	public boolean ajouter(int qteAchetee) {
		boolean b_qteAchetee= false;
		if(qteAchetee > 0){
			this.quantiteStock += qteAchetee;
			b_qteAchetee = true;
		}
		return b_qteAchetee;
	}

	@Override
	public boolean enlever(int qteVendue) {
		boolean b_qteVendue = false;
		if(this.quantiteStock >= qteVendue && qteVendue > 0){
			this.quantiteStock -= qteVendue;
			b_qteVendue = true;
		}
		return b_qteVendue;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public int getQuantite() {
		// TODO Auto-generated method stub
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		// TODO Auto-generated method stub
		return this.prixUnitaireHT ;
		//return df.format(this.prixUnitaireHT);
	}

	@Override
	public double getPrixUnitaireTTC() {
		// TODO Auto-generated method stub
		return this.prixUnitaireHT * this.tauxTVA + this.prixUnitaireHT ;
	}

	@Override
	public double getPrixStockTTC() {
		// TODO Auto-generated method stub.
		return getPrixUnitaireTTC() * this.quantiteStock;
		//return this.prixUnitaireHT * this.tauxTVA * this.quantiteStock ;
	}

	@Override
	public String toString() {
		// Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock :
		return this.nom + " - prix HT : "+nf.format(prixUnitaireHT )+ " - prix TTC : "
				+ nf.format(getPrixUnitaireTTC() ) + " - quantité en stock : "
				+quantiteStock;
	}
	
	

}
