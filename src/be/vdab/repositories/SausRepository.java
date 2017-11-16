package be.vdab.repositories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import be.vdab.entities.Saus;

public class SausRepository extends AbstractRepository implements Serializable  {
	private final static long serialVersionUID = 1L;
	
	// sql statements
	
	private static final String FROM_SELECT = 
			"from sauzeningredi�nten inner join sauzen on sauzen.id = sauzeningredi�nten.sausnummer "
					+ "inner join ingredi�nten on ingredi�nten.id = sauzeningredi�nten.ingredi�ntnummer ";
	
//	private static final String SELECT_INGREDI�NTEN_PER_SAUS = 
//		"select ingredi�nten.naam as naam "
//		+ FROM_SELECT 
//		+ "where sauzen.id = ?";
	
	private static final String SELECT_SAUSNAAM_PER_INGREDI�NT =
		"select sauzen.naam as naam "
		+ FROM_SELECT
		+ "where ingredi�nten.naam = ?";
	
//	private static final String REMOVE_SAUS_BY_ID_FROM_SAUZEN =
//		"delete from sauzen where id = ?";
//	
//	private static final String REMOVE_SAUS_BY_ID_FROM_SAUZENINGREDI�NTEN = 
//		"delete from sauzeningredi�nten where sausnummer = ?";
	
	private static final String REMOVE_SAUS_BY_IDS_FROM_SAUZEN =
			"delete from sauzen where id in (";
		
		private static final String REMOVE_SAUS_BY_IDS_FROM_SAUZENINGREDI�NTEN = 
			"delete from sauzeningredi�nten where sausnummer in (";
			
	private static final String SELECT_ALL_SAUSNAAM =
		"select naam from sauzen";
	
	private static final String SELECT_ALL_SAUZEN_INGREDI�NTEN = 
			"select sauzen.id as id, sauzen.naam as sausnaam, ingredi�nten.naam as ingredi�ntnaam "
			+ FROM_SELECT + 
			" order by sauzen.id";
	
	// functies
	
	public List<String> findAllSausNaam() {
		try(Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement()) {
			List<String> sausNamen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try(ResultSet resultSet = statement.executeQuery(SELECT_ALL_SAUSNAAM)) {
				while(resultSet.next()) {
					sausNamen.add(resultSet.getString("naam"));
				}
			}
			connection.commit();
			return sausNamen;
		}
		catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<Saus> findAll() {
		try(Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement()) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try(ResultSet resultSet = statement.executeQuery(SELECT_ALL_SAUZEN_INGREDI�NTEN)) {
				List<Saus> sauzen = new ArrayList<>();
				int id = 0;
				Saus saus = new Saus();
				while(resultSet.next()) {
					if(id != resultSet.getInt("id")) {
						if(id != 0) {
							sauzen.add(saus);
							saus = new Saus();
						}						
						saus.setNummer((long)(resultSet.getInt("id")));
						saus.setNaam(resultSet.getString("sausnaam"));			
					}
					saus.addIngredi�nt(resultSet.getString("ingredi�ntnaam"));
					id = resultSet.getInt("id");
				}
				sauzen.add(saus);
				connection.commit();
				return sauzen;
			}
		}
		catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public List<String> findSausNaamByIngredi�nt(String ingredi�nt) {
		try(Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_SAUSNAAM_PER_INGREDI�NT)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, ingredi�nt);
			List<String> sausNamen = new ArrayList<>();
			try(ResultSet resultSet = statement.executeQuery()) {				
				while(resultSet.next()) {
					sausNamen.add(resultSet.getString("naam"));
				}
			}
			connection.commit();
			return sausNamen;
		}
		catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public void removeByIds(Set<Long> ids) {
		StringBuilder deleteIdsFromSauzen = new StringBuilder();
		deleteIdsFromSauzen.append(REMOVE_SAUS_BY_IDS_FROM_SAUZEN);
		StringBuilder deleteIdsFromSauzenIngredi�nten = new StringBuilder();
		deleteIdsFromSauzenIngredi�nten.append(REMOVE_SAUS_BY_IDS_FROM_SAUZENINGREDI�NTEN);
		for(long id: ids) {
			deleteIdsFromSauzen.append("?,");
			deleteIdsFromSauzenIngredi�nten.append("?,");
		}
		deleteIdsFromSauzen.replace(deleteIdsFromSauzen.length()-1, deleteIdsFromSauzen.length(), ")");
		deleteIdsFromSauzenIngredi�nten.replace(deleteIdsFromSauzenIngredi�nten.length()-1, deleteIdsFromSauzenIngredi�nten.length(), ")");
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement statementSauzen = connection.prepareStatement(deleteIdsFromSauzen.toString());
			PreparedStatement statementSauzenIngredi�nten = connection.prepareStatement(deleteIdsFromSauzenIngredi�nten.toString())) {
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);			
			int index = 1;
			for(long id: ids) {
				statementSauzen.setLong(index, id);
				statementSauzenIngredi�nten.setLong(index, id);
				index++;
			}
			statementSauzenIngredi�nten.executeUpdate();
			statementSauzen.executeUpdate();
			connection.commit();			
		}
		catch(SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	
//	public void removeById(long id) {
//		try(Connection connection = dataSource.getConnection();
//			PreparedStatement statementSauzen = connection.prepareStatement(REMOVE_SAUS_BY_ID_FROM_SAUZEN);
//			PreparedStatement statementSauzenIngredi�nten = connection.prepareStatement(REMOVE_SAUS_BY_ID_FROM_SAUZENINGREDI�NTEN)) {
//			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//			connection.setAutoCommit(false);
//			statementSauzen.setLong(1, id);
//			statementSauzenIngredi�nten.setLong(1, id);
//			statementSauzenIngredi�nten.executeUpdate();
//			statementSauzen.executeUpdate();
//			connection.commit();			
//		}
//		catch(SQLException ex) {
//			throw new RepositoryException(ex);
//		}
//	}
	
	// nieuwe functie findAllNaam() maken
	
//	private final static Map<Long,Saus> SAUZEN = new ConcurrentHashMap<>();
//	static {
//		SAUZEN.put(1L, (new Saus(1L, "cocktail", Arrays.asList("ketchup","mayonaise","whisky"))));
//		SAUZEN.put(2L, (new Saus(2L,"mayonaise", Arrays.asList("ei","mosterd","citroen","olijfolie"))));
//		SAUZEN.put(3L, (new Saus(3L,"mosterd", Arrays.asList("mosterdzaad","olie"))));
//		SAUZEN.put(4L, (new Saus(4L,"tartare", Arrays.asList("ei","mosterd","olijfolie","basilicum"))));
//		SAUZEN.put(5L, (new Saus(5L,"vinaigrette", Arrays.asList("mosterd","olijfolie","azijn","basilicum"))));		
//	}
//	
//	public List<Saus> findAll(){
//		return new ArrayList<>(SAUZEN.values());
//	}
//	
//	public List<Saus> findByIngredi�nt(String ingredi�nt){
//		return SAUZEN.values().stream()
//				.filter(saus -> saus.getIngredi�nten().stream().anyMatch(ingred -> ingred.equalsIgnoreCase(ingredi�nt)))
//				.collect(Collectors.toList());
//	}
//	
//	public void removeById(long id) {
//		SAUZEN.remove(id);
//	}
//	
//	public Saus findById(long id) {
//		return SAUZEN.get(id);
//	}
}
