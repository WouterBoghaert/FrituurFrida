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
			"from sauzeningrediŽnten inner join sauzen on sauzen.id = sauzeningrediŽnten.sausnummer "
					+ "inner join ingrediŽnten on ingrediŽnten.id = sauzeningrediŽnten.ingrediŽntnummer ";
	
//	private static final String SELECT_INGREDIňNTEN_PER_SAUS = 
//		"select ingrediŽnten.naam as naam "
//		+ FROM_SELECT 
//		+ "where sauzen.id = ?";
	
	private static final String SELECT_SAUSNAAM_PER_INGREDIňNT =
		"select sauzen.naam as naam "
		+ FROM_SELECT
		+ "where ingrediŽnten.naam = ?";
	
//	private static final String REMOVE_SAUS_BY_ID_FROM_SAUZEN =
//		"delete from sauzen where id = ?";
//	
//	private static final String REMOVE_SAUS_BY_ID_FROM_SAUZENINGREDIňNTEN = 
//		"delete from sauzeningrediŽnten where sausnummer = ?";
	
	private static final String REMOVE_SAUS_BY_IDS_FROM_SAUZEN =
			"delete from sauzen where id in (";
		
		private static final String REMOVE_SAUS_BY_IDS_FROM_SAUZENINGREDIňNTEN = 
			"delete from sauzeningrediŽnten where sausnummer in (";
			
	private static final String SELECT_ALL_SAUSNAAM =
		"select naam from sauzen";
	
	private static final String SELECT_ALL_SAUZEN_INGREDIňNTEN = 
			"select sauzen.id as id, sauzen.naam as sausnaam, ingrediŽnten.naam as ingrediŽntnaam "
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
			try(ResultSet resultSet = statement.executeQuery(SELECT_ALL_SAUZEN_INGREDIňNTEN)) {
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
					saus.addIngrediŽnt(resultSet.getString("ingrediŽntnaam"));
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
	
	public List<String> findSausNaamByIngrediŽnt(String ingrediŽnt) {
		try(Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_SAUSNAAM_PER_INGREDIňNT)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, ingrediŽnt);
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
		StringBuilder deleteIdsFromSauzenIngrediŽnten = new StringBuilder();
		deleteIdsFromSauzenIngrediŽnten.append(REMOVE_SAUS_BY_IDS_FROM_SAUZENINGREDIňNTEN);
		for(long id: ids) {
			deleteIdsFromSauzen.append("?,");
			deleteIdsFromSauzenIngrediŽnten.append("?,");
		}
		deleteIdsFromSauzen.replace(deleteIdsFromSauzen.length()-1, deleteIdsFromSauzen.length(), ")");
		deleteIdsFromSauzenIngrediŽnten.replace(deleteIdsFromSauzenIngrediŽnten.length()-1, deleteIdsFromSauzenIngrediŽnten.length(), ")");
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement statementSauzen = connection.prepareStatement(deleteIdsFromSauzen.toString());
			PreparedStatement statementSauzenIngrediŽnten = connection.prepareStatement(deleteIdsFromSauzenIngrediŽnten.toString())) {
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);			
			int index = 1;
			for(long id: ids) {
				statementSauzen.setLong(index, id);
				statementSauzenIngrediŽnten.setLong(index, id);
				index++;
			}
			statementSauzenIngrediŽnten.executeUpdate();
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
//			PreparedStatement statementSauzenIngrediŽnten = connection.prepareStatement(REMOVE_SAUS_BY_ID_FROM_SAUZENINGREDIňNTEN)) {
//			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//			connection.setAutoCommit(false);
//			statementSauzen.setLong(1, id);
//			statementSauzenIngrediŽnten.setLong(1, id);
//			statementSauzenIngrediŽnten.executeUpdate();
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
//	public List<Saus> findByIngrediŽnt(String ingrediŽnt){
//		return SAUZEN.values().stream()
//				.filter(saus -> saus.getIngrediŽnten().stream().anyMatch(ingred -> ingred.equalsIgnoreCase(ingrediŽnt)))
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
