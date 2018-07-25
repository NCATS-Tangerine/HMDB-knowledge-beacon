package db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import apimodels.BeaconConceptCategory;

/**
 * Query SQLite database for categories.
 *
 */
public class BeaconConceptCategoryQuery {


	private static final String SQL = 
		"SELECT BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID, BEACON_CONCEPT_CATEGORY.ID, "
		+ "  BEACON_CONCEPT_CATEGORY.URI, BEACON_CONCEPT_CATEGORY.CATEGORY, BEACON_CONCEPT_CATEGORY.LOCAL_ID, "
		+ "  BEACON_CONCEPT_CATEGORY.LOCAL_URI, BEACON_CONCEPT_CATEGORY.LOCAL_CATEGORY, "
		+ "  BEACON_CONCEPT_CATEGORY.DESCRIPTION, COUNT(BEACON_CONCEPT.ID) AS FREQUENCY "
		+ "FROM BEACON_CONCEPT_CATEGORY "
		+ "INNER JOIN BEACON_CONCEPT "
		+ "  ON BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID "
		+ "GROUP BY BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID";

	
	public static ArrayList<BeaconConceptCategory> execute() throws Exception {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(SQL);
			ArrayList<BeaconConceptCategory> categories = new ArrayList<BeaconConceptCategory>();
			while (res.next()) {
				categories.add(createBeaconConceptType(res));
			}
			return categories;
		} finally {
			if (con != null) con.close();
		}
	}
	
	
	private static BeaconConceptCategory createBeaconConceptType(ResultSet res) throws SQLException {
		BeaconConceptCategory type = new BeaconConceptCategory();
		type.setId(res.getString("ID"));
		type.setUri(res.getString("URI"));
		type.setCategory(res.getString("CATEGORY"));
		type.setLocalId(res.getString("LOCAL_ID"));
		type.setLocalUri(res.getString("LOCAL_URI"));
		type.setLocalCategory(res.getString("LOCAL_CATEGORY"));
		type.setDescription(res.getString("DESCRIPTION"));
		type.setFrequency(res.getInt("FREQUENCY"));
		return type;
	}
	

}
