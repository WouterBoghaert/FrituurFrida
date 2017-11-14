package be.vdab.repositories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SausRepository extends AbstractRepository implements Serializable  {
	private final static long serialVersionUID = 1L;
	
	// sql statements
	
	private static final String FROM_SELECT = 
			"from sauzeningredi�nten inner join sauzen on sauzen.id = sauzeningredi�nten.sausnummer "
					+ "inner join ingredi�nten on ingredi�nten.id = sauzeningredi�nten.ingredi�ntnummer ";
	
	private static final String SELECT_INGREDI�NTEN_PER_SAUS = 
		"select ingredi�nten.naam as naam "
		+ FROM_SELECT 
		+ "where sauzen.id = ?";
	
	private static final String SELECT_SAUSNAAM_PER_INGREDI�NT =
		"select sauzen.naam as naam "
		+ FROM_SELECT
		+ "where ingredi�nten.naam = ?";
	
	private static final String REMOVE_SAUS_BY_ID =
		"delete from sauzen where id = ?";
	
	private static final String SELECT_ALL_SAUSNAAM =
		"select naam from sauzen";
	
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
