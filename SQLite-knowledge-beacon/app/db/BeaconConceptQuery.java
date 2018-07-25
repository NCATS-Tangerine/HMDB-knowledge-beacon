package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import apimodels.BeaconConcept;

import static db.DBconnection.inS;

/**
 * Query SQLite database for concepts.
 *
 */
public class BeaconConceptQuery {
	
	
	private static String conceptSQL(int conceptId) {
		return "SELECT BEACON_CONCEPT_CATEGORY.CATEGORY, BEACON_CONCEPT.ID, BEACON_CONCEPT.NAME, BEACON_CONCEPT.DESCRIPTION  FROM BEACON_CONCEPT "
				+ "INNER JOIN BEACON_CONCEPT_CATEGORY "
				+ "  ON BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID "
				+ "WHERE BEACON_CONCEPT_ID = "+conceptId;
	}
	
	
	private static String idSQL(List<String> keywords, List<String> categories) {
		String select = "SELECT DISTINCT BEACON_CONCEPT.BEACON_CONCEPT_ID FROM BEACON_CONCEPT "
				+ "INNER JOIN BEACON_CONCEPT_CATEGORY "
				+ "  ON BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID "
				+ "LEFT JOIN BEACON_CONCEPT_SYNONYM "
				+ "  ON BEACON_CONCEPT_SYNONYM.BEACON_CONCEPT_ID = BEACON_CONCEPT.BEACON_CONCEPT_ID ";
		String keywordWhere = keywordWhere(keywords);
		String gategoryWhere = gategoryWhere(categories);
		String orderBy = " ORDER BY BEACON_CONCEPT.BEACON_CONCEPT_ID";
		if (keywordWhere == null)
			if (gategoryWhere == null)
				return select + orderBy;
			else
				return select + " WHERE "+gategoryWhere + orderBy;
		else
			if (gategoryWhere == null)
				return select + " WHERE "+keywordWhere + orderBy;
			else
				return select + " WHERE ("+keywordWhere+") AND ("+gategoryWhere+")" + orderBy;
	}

	
	private static String keywordWhere(List<String> keywords) {
		if (keywords.size() == 0 || (keywords.size() == 1 && ("*".equals(keywords.get(0)) || keywords.get(0).length() == 0))){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keywords.size(); i++){
			if (i > 0) sb.append(" OR ");
			sb.append("BEACON_CONCEPT.ID LIKE '%"+keywords.get(i)+"%'");
			sb.append(" OR BEACON_CONCEPT.NAME LIKE '%"+keywords.get(i)+"%'");
			sb.append(" OR BEACON_CONCEPT_SYNONYM.SYNONYM LIKE '%"+keywords.get(i)+"%'");
		}
		return sb.toString();
	}
	
	
	private static String gategoryWhere(List<String> categories) {
		if (categories.size()==0) return null;
		return "BEACON_CONCEPT_CATEGORY.CATEGORY"+inS(categories);
	}
	
	
	private static ArrayList<Integer> conceptIds(List<String> keywords, List<String> categories, Integer size) 
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			ArrayList<Integer> conceptIds = new ArrayList<Integer>(Math.min(100000, size));
			con = DBconnection.getConnection();
			Statement stmt = con.createStatement();
			String sql = idSQL(keywords, categories);
			ResultSet res = stmt.executeQuery(sql);
			int i = 0;
			while (res.next() && i < size) {
				conceptIds.add(res.getInt("BEACON_CONCEPT_ID"));
				i = i + 1;
			}
			return conceptIds;
		} finally {
			if (con != null)
				con.close();
		}
	}
	
	
	static BeaconConcept getConcept(int conceptId, Connection con) 
	throws SQLException, ClassNotFoundException {
		BeaconConcept beaconConcept = new BeaconConcept();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(conceptSQL(conceptId));
		if (res.next()) {
			beaconConcept.setId(res.getString("ID"));
			beaconConcept.setName(res.getString("NAME"));
			beaconConcept.setCategories(Arrays.asList(res.getString("CATEGORY")));
			beaconConcept.setDescription(res.getString("DESCRIPTION"));
			return beaconConcept;
		} else
			return null;
	}
	
	
	public static ArrayList<BeaconConcept> execute(List<String> keywords, List<String> categories, Integer size)
	throws SQLException, ClassNotFoundException {
		ArrayList<Integer> conceptIds = conceptIds(keywords, categories, size);
		ArrayList<BeaconConcept> concepts = new ArrayList<BeaconConcept>(conceptIds.size());
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			for (int conceptId : conceptIds){
				concepts.add(getConcept(conceptId, con));
			}
			return concepts;
		} finally {
			if (con != null) con.close();
		}

	}
	
}
