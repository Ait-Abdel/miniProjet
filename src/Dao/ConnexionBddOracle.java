package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBddOracle {

	private static Connection connection;

	private static void SeConnecterBddOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
			String login = "";
			String mdp = "";

			connection = DriverManager.getConnection(url, login, mdp);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		if (connection == null) {
			SeConnecterBddOracle();
		}
		return connection;
	}


}
