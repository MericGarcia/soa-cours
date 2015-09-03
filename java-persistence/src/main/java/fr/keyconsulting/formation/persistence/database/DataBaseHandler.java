package fr.keyconsulting.formation.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler {
	
	private String dbURL;
	private static DataBaseHandler instanceUnique;
	private Connection connection;

	
	/**
	 * Retourne de l'instance unique.
	 */
	public final static synchronized DataBaseHandler getInstance() {
		if (instanceUnique == null) {
			instanceUnique = new DataBaseHandler();
		}
		return instanceUnique;
	}

	/**
	 * Constructeur (singleton).
	 */
	protected DataBaseHandler() {
		this.connection = null;
		this.dbURL = null;
	}

	public void init(String driver, String url) throws ClassNotFoundException {
		Class.forName(driver);
		this.dbURL = url;
	}
	
	public void connect(String user, String password) throws SQLException {
		connection = DriverManager.getConnection(dbURL, user, password);
		if (connection == null) {
			throw new SQLException("Impossible d'obtenir une connexion");
		}
		connection.setAutoCommit(false);
	}

	public void commit() throws SQLException {
		connection.commit();
	}

	public void rollback() throws SQLException {
		connection.rollback();
	}

	public void disconnect() throws SQLException {
		if (this.connection != null) {
			commit();
			connection.setAutoCommit(true);
			this.connection.close();
			this.connection = null;
		} 

	}
	
    public Connection getConnection() {
        return this.connection;
    }


}
