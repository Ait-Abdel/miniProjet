package Dao;
import java.util.List;
import metier.*;

public interface I_ProduitDAO {
	public abstract List<I_Produit> getToutLesProduits() throws DAOException;

	public abstract boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock) throws DAOException;

	public abstract boolean deleteProduit(String nom) throws DAOException;

	public abstract boolean enleverQuantiteProduit(String nom, int quantiteStock) throws DAOException;
	
	public abstract boolean ajouterQuantiteProduit(String nom, int quantiteStock) throws DAOException;
	
	public abstract boolean deleteAll() throws DAOException;
	
	public abstract I_Produit getProduit(String nom) throws DAOException;
	
	public abstract void fermerAccesAuDonnees();

}
