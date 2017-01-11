package application;

import java.util.ArrayList;

import presentation.FenetrePrincipale;
import metier.*;

public class Controleur {

	private I_Catalogue cat;
	private static ControleurNouveauSuppression cns;
	private static ControleurAchatVente cav;
	private static ControleurStock cs;
	
	public Controleur() {
		cat = new Catalogue();
		cns = new ControleurNouveauSuppression(cat);
		cav = new ControleurAchatVente(cat);
		cs = new ControleurStock(cat);	
	}
	
	/*public static void main(String[] args) {
		new Controleur();
		new FenetrePrincipale(cns,cav);/*,cav,cs);
	}*/

	public I_Catalogue getCat() {
		return cat;
	}

	public static ControleurNouveauSuppression getCns() {
		return cns;
	}

	public static ControleurAchatVente getCav() {
		return cav;
	}
	public static ControleurStock getCs(){
		return cs;
	}


	



	
}
