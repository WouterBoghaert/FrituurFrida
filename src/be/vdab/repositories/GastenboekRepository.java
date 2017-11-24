package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.vdab.entities.GastenboekEntry;

public class GastenboekRepository extends AbstractRepository {
	private static final String FIND_ALL = 
		"select id, datum, naam, bericht from gastenboek " + 
		"order by datum desc, id desc";
	private static final String TOEVOEGEN = 
		"insert into gastenboek(datum,naam,bericht) " + 
		"values(?, ?, ?)";
	private static final String VERWIJDEREN = 
		"delete from gastenboek where id in (";
	
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
		if(GastenboekEntry.isNaamValid(naam) && GastenboekEntry.isBerichtValid(bericht)) {
			try(Connection connection  = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(TOEVOEGEN)) {
				connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);
				LocalDateTime datum = LocalDateTime.now();
				statement.setTimestamp(1, java.sql.Timestamp.valueOf(datum));
				statement.setString(2, naam);
				statement.setString(3, bericht);
				statement.executeUpdate();
				connection.commit();
			}
			catch(SQLException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
	
	public void verwijderen(String [] ids) {
		if(Arrays.stream(ids).allMatch(id -> GastenboekEntry.isIdValid(Long.parseLong(id)))) {
			StringBuilder builder = new StringBuilder();
			builder.append(VERWIJDEREN);
			Arrays.stream(ids).forEach(id -> builder.append("?,"));
			builder.setCharAt(builder.length()-1, ')');
			
			try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(builder.toString())) {
				connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);
				int index = 0;
				for (String id : ids) {
					statement.setLong(++index, Long.parseLong(id));
				}
				statement.executeUpdate();
				connection.commit();
			}
			catch(SQLException ex) {
				throw new RepositoryException(ex);
			}
		}		
	}
}
