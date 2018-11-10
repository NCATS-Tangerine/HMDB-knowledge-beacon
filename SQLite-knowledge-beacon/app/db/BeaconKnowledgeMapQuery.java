package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import apimodels.BeaconKnowledgeMapObject;
import apimodels.BeaconKnowledgeMapPredicate;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconKnowledgeMapSubject;


/**
 * Query SQLite database for knowledge map.
 *
 */
public class BeaconKnowledgeMapQuery {


	private static String masterPrefixQuery =
			"  SELECT DISTINCT "
			+ "  BEACON_CONCEPT_CATEGORY.CATEGORY, "
			+ "  substr(BEACON_CONCEPT.ID, 1, instr(BEACON_CONCEPT.ID,':')-1) AS PREFIX "
			+ "FROM BEACON_CONCEPT "
			+ "INNER JOIN BEACON_CONCEPT_CATEGORY "
			+ "  ON BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID "
			+ "WHERE BEACON_CONCEPT.ID != ''";
	
	private static String synonymPrefixQuery =
			"SELECT DISTINCT BEACON_CONCEPT_CATEGORY.CATEGORY, substr(SYNONYM, 1, instr(SYNONYM,':')-1) AS PREFIX "
			+ "FROM BEACON_CONCEPT_SYNONYM "
			+ "INNER JOIN BEACON_CONCEPT "
			+ "  ON BEACON_CONCEPT_SYNONYM.BEACON_CONCEPT_ID = BEACON_CONCEPT.BEACON_CONCEPT_ID "
			+ "INNER JOIN BEACON_CONCEPT_CATEGORY "
			+ "  ON BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = BEACON_CONCEPT.BEACON_CONCEPT_CATEGORY_ID "
			+ "WHERE EXACT_MATCH = 1 "
			+ "ORDER BY BEACON_CONCEPT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID";
	
	private static String knowledgeMapQuery = 
			"SELECT SUBJECT_CATEGORY.CATEGORY AS SUBJECT_CATEGORY, BEACON_PREDICATE.EDGE_LABEL, BEACON_PREDICATE.RELATION, "
			+ "  BEACON_STATEMENT.NEGATED, BEACON_PREDICATE.DEFINITION, "
			+ "  OBJECT_CATEGORY.CATEGORY AS OBJECT_CATEGORY, COUNT(BEACON_STATEMENT.BEACON_STATEMENT_ID) AS FREQUENCY "
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

	private static void addPrefixes(Map<String, ArrayList<String>> prefixes, Statement stmt, String query) throws SQLException{
		ResultSet res = stmt.executeQuery(query);
		while (res.next()){
			String category = res.getString("CATEGORY");
			String prefix = res.getString("PREFIX");
			if (!prefixes.containsKey(category)){
				prefixes.put(category, new ArrayList<String>());
			}
			prefixes.get(category).add(prefix);
		}
	}
	
	static Map<String, ArrayList<String>> getPrefixes(Connection con) throws SQLException, ClassNotFoundException {
		Map<String, ArrayList<String>> prefixes = new HashMap<String, ArrayList<String>>();
		Statement stmt = con.createStatement();
		addPrefixes(prefixes, stmt, masterPrefixQuery);
		stmt.close();
		stmt = con.createStatement();
		addPrefixes(prefixes, stmt, synonymPrefixQuery);
		stmt.close();
		return prefixes;
	}
	
	
	public static ArrayList<BeaconKnowledgeMapStatement> execute() throws SQLException, ClassNotFoundException{
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			Map<String, ArrayList<String>> prefixes = getPrefixes(con);
			ArrayList<BeaconKnowledgeMapStatement> map = new ArrayList<BeaconKnowledgeMapStatement>();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(knowledgeMapQuery);
			while (res.next()){
				BeaconKnowledgeMapSubject subject = new BeaconKnowledgeMapSubject();
				subject.setCategory(res.getString("SUBJECT_CATEGORY"));
				subject.setPrefixes(prefixes.getOrDefault(subject.getCategory(),new ArrayList<String>()));
				BeaconKnowledgeMapPredicate predicate = new BeaconKnowledgeMapPredicate();
				predicate.setEdgeLabel(res.getString("EDGE_LABEL"));
				predicate.setRelation(res.getString("RELATION"));
				predicate.setNegated(res.getBoolean("NEGATED"));
				BeaconKnowledgeMapObject object = new BeaconKnowledgeMapObject();
				object.setCategory(res.getString("OBJECT_CATEGORY"));
				object.setPrefixes(prefixes.getOrDefault(object.getCategory(),new ArrayList<String>()));
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



