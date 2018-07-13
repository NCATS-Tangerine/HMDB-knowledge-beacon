package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import apimodels.BeaconConceptDetail;
import apimodels.BeaconConceptWithDetails;

/**
 * Query SQLite database for concept details.
 *
 */
public class BeaconConceptDetailsQuery {

	private static String synonymSQL(int conceptId) {
		return "SELECT BEACON_CONCEPT_ID, SYNONYM FROM BEACON_CONCEPT_SYNONYM "
				+ "WHERE EXACT_MATCH = 0 AND BEACON_CONCEPT_ID = "+conceptId;
	}
	
	
	private static String exactMatchesSQL(int conceptId) {
		return "SELECT BEACON_CONCEPT_ID, SYNONYM FROM BEACON_CONCEPT_SYNONYM "
				+ "WHERE EXACT_MATCH = 1 AND BEACON_CONCEPT_ID = "+conceptId;
	}
	
	
	private static String detailsSQL(int conceptId){
		return "SELECT BEACON_CONCEPT_ID, TAG, VALUE FROM BEACON_CONCEPT_DETAIL "
				+ "WHERE BEACON_CONCEPT_ID = "+conceptId;		
	}
	
	
	private static String conceptSQL(String conceptId) {
		return "SELECT BEACON_CONCEPT.BEACON_CONCEPT_ID, BEACON_CONCEPT.ID, BEACON_CONCEPT.URI, BEACON_CONCEPT.NAME, BEACON_CONCEPT.SYMBOL, BEACON_CONCEPT_CATEGORY.CATEGORY, BEACON_CONCEPT.DESCRIPTION "
				+ "FROM BEACON_CONCEPT "
				+ "INNER JOIN BEACON_CONCEPT_CATEGORY "
				+ "  ON BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID "
				+ "WHERE BEACON_CONCEPT.ID = '"+conceptId+"'";
	}
	
	
	static ArrayList<String> synonyms(int conceptId, Connection con) 
	throws SQLException {
		ArrayList<String> synonyms = new ArrayList<String>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(synonymSQL(conceptId));
		while (res.next()){
			synonyms.add(res.getString("SYNONYM"));
		}
		return synonyms;
	}	
	
	
	static ArrayList<String> exactMatches(int conceptId, Connection con) 
	throws SQLException {
		ArrayList<String> synonyms = new ArrayList<String>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(exactMatchesSQL(conceptId));
		while (res.next()){
			synonyms.add(res.getString("SYNONYM"));
		}
		return synonyms;
	}	
	
	
	private static ArrayList<BeaconConceptDetail> details(int conceptId, Connection con) 
	throws SQLException {
		ArrayList<BeaconConceptDetail> details = new ArrayList<BeaconConceptDetail>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(detailsSQL(conceptId));
		while (res.next()){
			BeaconConceptDetail detail = new BeaconConceptDetail();
			detail.setTag(res.getString("TAG"));
			detail.setValue(res.getString("VALUE"));
			details.add(detail);
		}
		return details;
	}
	
	
	public static BeaconConceptWithDetails getConceptWithDetails(String conceptId) 
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(conceptSQL(conceptId));
			BeaconConceptWithDetails beaconConcept = new BeaconConceptWithDetails();
			if (res.next()) {
				beaconConcept.setId(res.getString("ID"));
				beaconConcept.setUri(res.getString("URI"));
				beaconConcept.setName(res.getString("NAME"));
				beaconConcept.setSymbol(res.getString("SYMBOL"));
				beaconConcept.setCategory(res.getString("CATEGORY"));
				beaconConcept.setDescription(res.getString("DESCRIPTION"));
				ArrayList<String> synonyms = synonyms(res.getInt("BEACON_CONCEPT_ID"), con);
				beaconConcept.setSynonyms(synonyms);
				ArrayList<String> exactMatches = exactMatches(res.getInt("BEACON_CONCEPT_ID"), con);
				beaconConcept.setExactMatches(exactMatches);
				ArrayList<BeaconConceptDetail> details = details(res.getInt("BEACON_CONCEPT_ID"), con);
				beaconConcept.setDetails(details);
				return beaconConcept;			
			}
			beaconConcept.setName("NOT FOUND");
			return beaconConcept;
		} finally {
			if (con != null) con.close();
		}
	}
	
	

}
