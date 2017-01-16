package Dao;



public class ProduitFactory {
	
	public ProduitSqlDAO createProduitDAO(){
		return new ProduitSqlDAO();
	}

}
