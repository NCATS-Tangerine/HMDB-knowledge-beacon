package db;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import apimodels.*;
import static db.DBconnection.inS;
import static db.DBconnection.inI;

/**
 * Query SQLite database for statements.
 *
 */
public class BeaconStatementQuery {

	private static String conceptIdSQL(List<String> s) {
		String select = "SELECT DISTINCT BEACON_CONCEPT_ID FROM BEACON_CONCEPT ";
		return select + " WHERE ID" + inS(s);
	}
	
	
	private static String relationIdSQL(String relation) {
		String select = "SELECT DISTINCT BEACON_PREDICATE_ID FROM BEACON_PREDICATE";
		return select + " WHERE RELATION = '" +relation+"'";
	}
	

	private static String categoryIdSQL(List<String> relations) {
		String select = "SELECT DISTINCT BEACON_CONCEPT_CATEGORY_ID FROM BEACON_CONCEPT_CATEGORY";
		return select + " WHERE CATEGORY" + inS(relations);
	}
	
	
	private static String statementSQL(List<Integer> sourceIds, List<Integer> relationIds, List<Integer> targetIds, List<Integer> categoryIds){
		String select =
		  "SELECT BEACON_STATEMENT.BEACON_STATEMENT_ID, BEACON_STATEMENT.SUBJECT_CONCEPT_ID, "
		    + "BEACON_STATEMENT.BEACON_PREDICATE_ID, BEACON_STATEMENT.OBJECT_CONCEPT_ID, "
			+ "BEACON_PREDICATE.EDGE_LABEL, BEACON_PREDICATE.RELATION, BEACON_STATEMENT.NEGATED, "
			+ "OBJECT.ID AS OBJECT_ID, OBJECT.NAME AS OBJECT_NAME, OBJECT_CATEGORY.CATEGORY AS OBJECT_CATEGORY, "
			+ "SUBJECT.ID AS SUBJECT_ID, SUBJECT.NAME AS SUBJECT_NAME, SUBJECT_CATEGORY.CATEGORY AS SUBJECT_CATEGORY "
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
		    + "  ON SUBJECT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = SUBJECT.BEACON_CONCEPT_CATEGORY_ID";

		String relationClause = 
				(relationIds == null) ? "" : " BEACON_PREDICATE.BEACON_PREDICATE_ID" + inI(relationIds)+" AND";

		String subjectClause = 
				" ((SUBJECT_CONCEPT_ID" + inI(sourceIds) 
				+ ((targetIds==null)?"":" AND OBJECT_CONCEPT_ID" + inI(targetIds))
				+ ((categoryIds==null)?"":" AND OBJECT.BEACON_CONCEPT_CATEGORY_ID" + inI(categoryIds))+")";

		String objectClause = " (OBJECT_CONCEPT_ID" + inI(sourceIds) 
				+ ((targetIds==null)?"":" AND SUBJECT_CONCEPT_ID" + inI(targetIds))
				+ ((categoryIds==null)?"":" AND SUBJECT.BEACON_CONCEPT_CATEGORY_ID" + inI(categoryIds))+"))";
		
		return select + " WHERE" + relationClause + subjectClause + " OR " + objectClause;
	}

	
	private static ArrayList<Integer> conceptIds(List<String> s, Connection con)
	throws SQLException, ClassNotFoundException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(conceptIdSQL(s));
		while (res.next()){
			ids.add(res.getInt("BEACON_CONCEPT_ID"));
		}
		return ids;
	}
	
	
	private static ArrayList<Integer> relationIds(String relation, Connection con)
	throws SQLException, ClassNotFoundException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(relationIdSQL(relation));
		while (res.next()){
			ids.add(res.getInt("BEACON_PREDICATE_ID"));
		}
		return ids;
	}
	
	
	private static ArrayList<Integer> categoryIds(List<String> categories, Connection con)
	throws SQLException, ClassNotFoundException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(categoryIdSQL(categories));
		while (res.next()){
			ids.add(res.getInt("BEACON_CONCEPT_CATEGORY_ID"));
		}
		return ids;
	}
	
	
	private static BeaconStatement getStatement(ResultSet res) throws SQLException, ClassNotFoundException {
		BeaconStatementObject object = new BeaconStatementObject();
		object.setId(res.getString("OBJECT_ID"));
		object.setName(res.getString("OBJECT_NAME"));
		object.setCategory(res.getString("OBJECT_CATEGORY"));
		
		BeaconStatementPredicate predicate = new BeaconStatementPredicate();
		predicate.setEdgeLabel(res.getString("EDGE_LABEL"));
		predicate.setRelation(res.getString("RELATION"));
		predicate.setNegated(res.getBoolean("NEGATED"));
		
		BeaconStatementSubject subject = new BeaconStatementSubject();
		subject.setId(res.getString("SUBJECT_ID"));
		subject.setName(res.getString("SUBJECT_NAME"));
		subject.setCategory(res.getString("SUBJECT_CATEGORY"));
		
		BeaconStatement statement = new BeaconStatement();
		statement.setId(""+res.getInt("BEACON_STATEMENT_ID"));
		statement.setObject(object);
		statement.setPredicate(predicate);
		statement.setSubject(subject);
		return statement;
	}
	
	
	public static ArrayList<BeaconStatement> execute(List<String> s, String relation, List<String> t, List<String> keywords, List<String> categories, Integer size)
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			ArrayList<Integer> sourceIds = conceptIds(s, con);
			ArrayList<BeaconStatement> list = new ArrayList<BeaconStatement>();
			if (sourceIds.size() == 0) {
				return list;
			}
			ArrayList<Integer> relationIds = (relation == null || relation.equals("")) ? null : relationIds(relation, con);
			ArrayList<Integer> targetIds = (t == null || t.size() == 0) ? null : conceptIds(t, con);
			ArrayList<Integer> categoryIds = (categories == null || categories.size() == 0) ? null : categoryIds(categories, con);

			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(statementSQL(sourceIds, relationIds, targetIds, categoryIds));
			int i = 0;
			while (res.next() && i < size) {
				list.add(getStatement(res));
				i = i + 1;
			}
			stmt.close();
			return list;
		} finally {
			if (con != null)
				con.close();
		}
	}
	
}
