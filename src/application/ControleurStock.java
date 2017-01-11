package application;

import metier.I_Catalogue;

public class ControleurStock {
	private I_Catalogue cat;
	
	public ControleurStock(I_Catalogue cat) {
		this.cat = cat;
	}
	
	public String etatDesStocks(){
		return cat.toString();
	}

	
}
