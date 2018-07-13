package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import apimodels.ExactMatchResponse;

/**
 * Query SQLite database for exact matches.
 *
 */
public class ExactMatchQuery {
	
	private static String withinDomainSQL(String conceptId) {
		return "SELECT COUNT(BEACON_CONCEPT_ID) AS COUNT FROM BEACON_CONCEPT WHERE ID = '"+conceptId+"'";
	}
	
	private static String exactMatchSQL(String query) {
		return "SELECT DISTINCT BEACON_CONCEPT.ID "
				+ "FROM BEACON_CONCEPT_SYNONYM "
				+ "INNER JOIN BEACON_CONCEPT "
				+ "  ON BEACON_CONCEPT.BEACON_CONCEPT_ID = BEACON_CONCEPT_SYNONYM.BEACON_CONCEPT_ID "
				+ "WHERE BEACON_CONCEPT_SYNONYM.EXACT_MATCH = 1 "
				+ "AND BEACON_CONCEPT_SYNONYM.SYNONYM = '"+query+"'";
	}

	static ExactMatchResponse exactMatch(String query, Connection con)
	throws SQLException, ClassNotFoundException {
		ExactMatchResponse response = new ExactMatchResponse();
		response.setId(query);
		response.setWithinDomain(false);
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(withinDomainSQL(query));
		ArrayList<String> matches = new ArrayList<String>();
		if (res.next()){
			boolean withinDomain = res.getInt("COUNT")>0;
			response.setWithinDomain(withinDomain);
			if (withinDomain) {
				matches.add(query);
			}
		}
		res = stmt.executeQuery(exactMatchSQL(query));
		while (res.next()){
			matches.add(res.getString("ID"));
		}
		response.setHasExactMatches(matches);
		return response;
	}

	
	public static ArrayList<ExactMatchResponse> execute(List<String> queryList)
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			ArrayList<ExactMatchResponse> list = new ArrayList<ExactMatchResponse>();
			for (String query : queryList) {
				list.add(exactMatch(query, con));
			}
			return list;
		} finally {
			if (con != null)
				con.close();
		}
	}

}
