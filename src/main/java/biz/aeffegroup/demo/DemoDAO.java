package biz.aeffegroup.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DemoDAO {
	
	// PER ESEGUIRE UNA CONNESSIONE ALLA DB
	private Connection connection;
	// PER ESEGUIRE DELLE OPERAZIONI
	private Statement statement;
	// PER OTTENRE IL RISULTATO DELLE OPERAZIONI EFFETUATE
	private ResultSet resultset;
	// QUERY CHE VOGLIAMO
	private String query = "SELECT * FROM CLIENT;";
	private String updateQuery = "UPDATE CLIENT SET NAME = 'CIAO' WHERE ID = 1;";
	private String deleteQuery = "DELETE FROM CLIENT WHERE ID = 2;";
	
	public String read() throws SQLException {
		String ret = "";
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:office","SA",""); // DriverManager.getConnection(url = jdbc:h2/dadefinire, username, password)
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				ret += " " + resultset.getString("NAME");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			resultset.close();
			statement.close();
			connection.close();
		}
		return ret;
	}

	public String update() throws SQLException {
		String ret = "";
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:office", "SA", "");
			statement = connection.createStatement();
			resultset = statement.executeQuery(updateQuery);
			while(resultset.next()) {
				ret = resultset.getString("NAME");
			}
		}catch(SQLException e) {
			log.error(e.getMessage());
		}finally {
			resultset.close();
			statement.close();
			connection.close();
		}
		return ret;
	}

	public void delete() throws SQLException {
		try {
			connection = DriverManager.getConnection("jdbc:h2:mem:office", "SA", "");
			statement = connection.createStatement();
			resultset = statement.executeQuery(deleteQuery);
		}catch(SQLException e) {
			log.error(e.getMessage());
		}finally {
			resultset.close();
			statement.close();
			connection.close();
		}
		
	}
	
		

}


