package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.GastenboekEntry;

public class GastenboekRepository extends AbstractRepository {
	private static final String FIND_ALL = 
		"select id, datum, naam, bericht from gastenboek " + 
		"order by datum desc, id desc";
	private static final String TOEVOEGEN = 
		"insert into gastenboek(datum,naam,bericht) " + 
		"values(?, ?, ?)";
	
	public List<GastenboekEntry> findAll() {
		try(Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement()) {
			List<GastenboekEntry> gastenboek = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
				while(resultSet.next()) {
					gastenboek.add(new GastenboekEntry(
						resultSet.getLong("id"),
						resultSet.getTimestamp("datum").toLocalDateTime(),
						resultSet.getString("naam"),
						resultSet.getString("bericht")));
				}
			}
			connection.commit();
			return gastenboek;
		}
		catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public void toevoegen(String naam, String bericht) {
		if(naam != null && bericht != null && !naam.trim().isEmpty() && !bericht.trim().isEmpty()) {
			try(Connection connection  = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(TOEVOEGEN)) {
				connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);
				LocalDateTime datum = LocalDateTime.now();
				statement.setTimestamp(1, java.sql.Timestamp.valueOf(datum));
				statement.setString(2, naam);
				statement.setString(3, bericht);
				statement.execute();
				connection.commit();
			}
			catch(SQLException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
	
	// nakijken en testen

}
