package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import apimodels.BeaconKnowledgeMapObject;
import apimodels.BeaconKnowledgeMapPredicate;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconKnowledgeMapSubject;


/**
 * Query SQLite database for knowledge map.
 *
 */
public class BeaconKnowledgeMapQuery {

	
	private static String knowledgeMapQuery = 
			"SELECT SUBJECT_CATEGORY.CATEGORY AS SUBJECT_CATEGORY, BEACON_PREDICATE.EDGE_LABEL, BEACON_PREDICATE.RELATION, BEACON_STATEMENT.NEGATED, BEACON_PREDICATE.DEFINITION, "
			+ "OBJECT_CATEGORY.CATEGORY AS OBJECT_CATEGORY, COUNT(BEACON_STATEMENT.BEACON_STATEMENT_ID) AS FREQUENCY "
			+ "FROM BEACON_STATEMENT " 
			+ "INNER JOIN BEACON_PREDICATE "
			+ "  ON BEACON_PREDICATE.BEACON_PREDICATE_ID = BEACON_STATEMENT.BEACON_PREDICATE_ID "
			+ "INNER JOIN BEACON_CONCEPT OBJECT"
			+ "  ON OBJECT.BEACON_CONCEPT_ID=BEACON_STATEMENT.OBJECT_CONCEPT_ID "
			+ "INNER JOIN BEACON_CONCEPT_CATEGORY OBJECT_CATEGORY"
			+ "  ON OBJECT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = OBJECT.BEACON_CONCEPT_CATEGORY_ID "
			+ "INNER JOIN BEACON_CONCEPT SUBJECT"
			+ "  ON SUBJECT.BEACON_CONCEPT_ID=BEACON_STATEMENT.SUBJECT_CONCEPT_ID "
			+ "INNER JOIN BEACON_CONCEPT_CATEGORY SUBJECT_CATEGORY "
			+ "  ON SUBJECT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = SUBJECT.BEACON_CONCEPT_CATEGORY_ID "
			+ "GROUP BY SUBJECT_CATEGORY, EDGE_LABEL, RELATION, NEGATED, DEFINITION, OBJECT_CATEGORY";

	public static ArrayList<BeaconKnowledgeMapStatement> execute() throws SQLException, ClassNotFoundException{
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			ArrayList<BeaconKnowledgeMapStatement> map = new ArrayList<BeaconKnowledgeMapStatement>();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(knowledgeMapQuery);
			while (res.next()){
				BeaconKnowledgeMapSubject subject = new BeaconKnowledgeMapSubject();
				subject.setCategory(res.getString("SUBJECT_CATEGORY"));
				BeaconKnowledgeMapPredicate predicate = new BeaconKnowledgeMapPredicate();
				predicate.setEdgeLabel(res.getString("EDGE_LABEL"));
				predicate.setRelation(res.getString("RELATION"));
				predicate.setNegated(res.getBoolean("NEGATED"));
				BeaconKnowledgeMapObject object = new BeaconKnowledgeMapObject();
				object.setCategory(res.getString("OBJECT_CATEGORY"));
				BeaconKnowledgeMapStatement statement = new BeaconKnowledgeMapStatement();
				statement.setSubject(subject);
				statement.setPredicate(predicate);
				statement.setObject(object);
				statement.setFrequency(res.getInt("FREQUENCY"));
				statement.setDescription(res.getString("DEFINITION"));
				map.add(statement);
			}
			stmt.close();
			return map;		
		} finally {
			if (con != null)
				con.close();
		}
	}
}



