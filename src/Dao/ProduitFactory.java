package Dao;



public class ProduitFactory {
	
	public I_ProduitDAO createProduitDAO(){
		return new ProduitXmlDAO();
	}

}
