package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import apimodels.BeaconPredicate;

/**
 * Query SQLite database for predicates.
 *
 */
public class BeaconPredicateQuery {

	private static final String SQL = 
			"SELECT BEACON_PREDICATE.BEACON_PREDICATE_ID, BEACON_PREDICATE.ID, "
			+ "  BEACON_PREDICATE.URI, BEACON_PREDICATE.RELATION, BEACON_PREDICATE.EDGE_LABEL, "
			+ "  BEACON_PREDICATE.LOCAL_ID, BEACON_PREDICATE.LOCAL_URI, BEACON_PREDICATE.LOCAL_RELATION, "
			+ "  BEACON_PREDICATE.DEFINITION, COUNT(BEACON_STATEMENT.BEACON_STATEMENT_ID) AS FREQUENCY "
			+ "FROM BEACON_PREDICATE "
			+ "INNER JOIN BEACON_STATEMENT "
			+ "  ON BEACON_STATEMENT.BEACON_PREDICATE_ID = BEACON_PREDICATE.BEACON_PREDICATE_ID "
			+ "GROUP BY BEACON_PREDICATE.BEACON_PREDICATE_ID";

	
	private static BeaconPredicate createBeaconPredicate(ResultSet res) throws SQLException{
		BeaconPredicate predicate = new BeaconPredicate();
		predicate.setId(res.getString("ID"));
		predicate.setUri(res.getString("URI"));
		predicate.setRelation(res.getString("RELATION"));
		predicate.setEdgeLabel(res.getString("EDGE_LABEL"));
		predicate.setLocalId(res.getString("LOCAL_ID"));
		predicate.setLocalUri(res.getString("LOCAL_URI"));
		predicate.setLocalRelation(res.getString("LOCAL_RELATION"));
		predicate.setDescription(res.getString("DEFINITION"));
		predicate.setFrequency(res.getInt("FREQUENCY"));
		return predicate;
	}
	
	
	public static ArrayList<BeaconPredicate> execute() throws Exception {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(SQL);
			ArrayList<BeaconPredicate> predicates = new ArrayList<BeaconPredicate>();
			while (res.next()) {
				predicates.add(createBeaconPredicate(res));
			}
			res.close();
			return predicates;
		} finally {
			if (con != null)
				con.close();
		}
	}

}


