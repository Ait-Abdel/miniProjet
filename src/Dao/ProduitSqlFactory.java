package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class ProduitSqlFactory implements ProduitFactory {
	private Connection connection;

	public ProduitSqlFactory() {
		connection = ConnexionBddOracle.getConnection();
	}

	@Override
	public List<I_Produit> getToutLesProduits() {
		List<I_Produit> lesProduits = new ArrayList<I_Produit>();

		String req = "Select * from Produits";
		Statement statement = null;

		try {

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(req);
			while (resultSet.next()) {

				String nom = resultSet.getString("NOM");
				double prixUnitaireHT = resultSet.getInt("PRIXUNITAIREHT");
				int quantiteStock = resultSet.getInt("QUANTITESTOCK");

				Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
				lesProduits.add(produit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}

		return lesProduits;
	}

	@Override
	public boolean createProduit(String nom, double prixUnitaireHT, int quantiteStock) {
		boolean produitCreer = false;
		String req = "call nouveauProduit(?,?,?)";
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall(req);
			callableStatement.setString(1, nom);
			callableStatement.setInt(2, (int) prixUnitaireHT);
			callableStatement.setInt(3, quantiteStock);
			if (!callableStatement.execute())
				produitCreer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return produitCreer;
	}

	@Override
	public boolean deleteProduit(String nom) {
		boolean produitSupprimee = false;
		PreparedStatement preparedStatement = null;

		try {
			String res = "delete from Produits where nom = '" + nom + "'";
			preparedStatement = connection.prepareStatement(res);
			// preparedStatement.setString(1, nom);

			if (preparedStatement.executeUpdate() != 0)
				produitSupprimee = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		return produitSupprimee;
	}

	@Override
	public boolean enleverQuantiteProduit(String nom, int quantiteStock) {
		boolean qteEnlevee = false;
		Statement statement = null;
		I_Produit produit = getProduit(nom);
		if (produit.enlever(quantiteStock)) {

			try {
				String req = "Update Produits set QUANTITESTOCK  = " + produit.getQuantite() + " where nom = '"
						+ produit.getNom() + "' ";
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if (statement.executeUpdate(req) != 0)
					qteEnlevee = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeStatement(statement);
			}
		}

		return qteEnlevee;
	}

	@Override
	public boolean ajouterQuantiteProduit(String nom, int quantiteStock) {
		boolean qteAjoutee = false;
		Statement statement = null;
		String req = null;
		System.out.println("fkezfjksjflsjs");
		I_Produit produit = getProduit(nom);
		if (produit.ajouter(quantiteStock)) {
			req = "Update Produits set QUANTITESTOCK  = " + produit.getQuantite() + " where nom = '"+ produit.getNom() + "' ";
			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if (statement.executeUpdate(req) != 0)
					qteAjoutee = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeStatement(statement);
				
			}
		}
		

		return qteAjoutee;
	}

	private void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteAll() {
		boolean supprimertousLesProduits = false;
		PreparedStatement preparedStatement = null;

		try {
			String res = "delete from Produits ";
			preparedStatement = connection.prepareStatement(res);
			if (preparedStatement.executeUpdate() != 0)
				supprimertousLesProduits = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}

		return supprimertousLesProduits;
	}

	private void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	

	public I_Produit getProduit(String nom) {
		I_Produit produit = null;
		Statement statement = null;
		try {
			String req = "Select *  from Produits where nom = '" + nom + "'";
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(req);
			if (resultSet.next()) {

				String nom1 = resultSet.getString("NOM");
				double prixUnitaireHT = resultSet.getInt("PRIXUNITAIREHT");
				int quantiteStock = resultSet.getInt("QUANTITESTOCK");

				produit = new Produit(nom1, prixUnitaireHT, quantiteStock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
		return produit;
	}

}
