package db;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	private static String relationIdSQL(String edgeLabel, String relation) {
		String select = "SELECT DISTINCT BEACON_PREDICATE_ID FROM BEACON_PREDICATE";
		if (edgeLabel == null) {
			return select + " WHERE RELATION = '" + relation + "'";
		}
		if (relation == null) {
			return select + " WHERE EDGE_LABEL = '" + edgeLabel + "'";
		}
		return select + " WHERE RELATION = '" + relation + " AND EDGE_LABEL = '" + edgeLabel + "'";
	}


	private static String categoryIdSQL(List<String> categories) {
		String select = "SELECT DISTINCT BEACON_CONCEPT_CATEGORY_ID FROM BEACON_CONCEPT_CATEGORY";
		return select + " WHERE CATEGORY" + inS(categories);
	}
	

	private static boolean hasKeywords(List<String> keywords) {
		if (keywords == null || keywords.size()==0)
			return false;
		if (keywords.size()==1 && ("*".equals(keywords.get(0)) || keywords.get(0).length() == 0))
			return false;
		return true;
	}
	

	private static String joinSynonyms(String subject, List<String> keywords) {
		if (hasKeywords(keywords)) {
			return "LEFT JOIN BEACON_CONCEPT_SYNONYM "+subject+"_SYNONYM "
				 + "  ON "+subject+"_SYNONYM.BEACON_CONCEPT_ID = "+subject+".BEACON_CONCEPT_ID ";
		}
		return "";
	}
	
	
	private static String conceptClause(String subject, List<Integer> ids, List<String> keywords, List<Integer> categoryIds) {
		String query = "";
		if (ids != null && ids.size() > 0) {
			query = subject+"_CONCEPT_ID" + inI(ids);
		}
		if (hasKeywords(keywords)) {
			if (query.length() > 0) {
				query += " AND " ;
			}
			StringBuffer sb = new StringBuffer("(");
			for (int i = 0; i < keywords.size(); i++){
				if (i > 0) sb.append(" OR ");
				sb.append(subject+".ID LIKE '%"+keywords.get(i)+"%'");
				sb.append(" OR "+subject+".NAME LIKE '%"+keywords.get(i)+"%'");
				sb.append(" OR "+subject+"_SYNONYM.SYNONYM LIKE '%"+keywords.get(i)+"%'");
			}
			sb.append(")");
			query += sb;
		}
		if (categoryIds != null && categoryIds.size() > 0) {
			if (query.length() > 0) {
				query += " AND " ;
			}
			query += subject+".BEACON_CONCEPT_CATEGORY_ID"+ inI(categoryIds);
		}		
		return query;
	}
	
	
	private static String statementSQL(
			List<Integer> sourceIds, List<String> sKeywords, List<Integer> sCategoryIds,
			List<Integer> relationIds, 
			List<Integer> targetIds, List<String> tKeywords, List<Integer> tCategoryIds){
		String select =
		  "SELECT DISTINCT BEACON_STATEMENT.BEACON_STATEMENT_ID, BEACON_STATEMENT.SUBJECT_CONCEPT_ID, "
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
		    + "  ON SUBJECT_CATEGORY.BEACON_CONCEPT_CATEGORY_ID = SUBJECT.BEACON_CONCEPT_CATEGORY_ID "
		    + joinSynonyms("SUBJECT", sKeywords)
		    + joinSynonyms("OBJECT", tKeywords);

		String whereClause = "";

		String objectClause = conceptClause("SUBJECT", sourceIds, sKeywords, sCategoryIds);
		if (objectClause.length() > 0) {
			whereClause = " WHERE " + objectClause;
		}

		if (relationIds != null) {
			whereClause += (whereClause.length() == 0) ? " WHERE " : " AND ";
			whereClause += " BEACON_PREDICATE.BEACON_PREDICATE_ID" + inI(relationIds);
		}

		String subjectClause = conceptClause("OBJECT", targetIds, tKeywords, tCategoryIds);
		if (subjectClause.length() > 0) {
			whereClause += (whereClause.length() == 0) ? " WHERE " : " AND ";
			whereClause += subjectClause;
		}

		String orderBy = " ORDER BY BEACON_STATEMENT.BEACON_STATEMENT_ID";

		return select + whereClause + orderBy;
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
	
	
	private static ArrayList<Integer> relationIds(String edgeLabel, String relation, Connection con)
	throws SQLException, ClassNotFoundException {
		if (edgeLabel.equals("")) {
			edgeLabel = null;
		}
		if (relation.equals("")) {
			relation = null;
		}
		if (edgeLabel == null && relation == null) {
			return null;
		}
		ArrayList<Integer> ids = new ArrayList<Integer>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(relationIdSQL(edgeLabel, relation));
		while (res.next()){
			ids.add(res.getInt("BEACON_PREDICATE_ID"));
		}
		return (ids.size() > 0)? ids : null;
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
		object.setCategories(Arrays.asList(res.getString("OBJECT_CATEGORY")));
		
		BeaconStatementPredicate predicate = new BeaconStatementPredicate();
		predicate.setEdgeLabel(res.getString("EDGE_LABEL"));
		predicate.setRelation(res.getString("RELATION"));
		predicate.setNegated(res.getBoolean("NEGATED"));
		
		BeaconStatementSubject subject = new BeaconStatementSubject();
		subject.setId(res.getString("SUBJECT_ID"));
		subject.setName(res.getString("SUBJECT_NAME"));
		subject.setCategories(Arrays.asList(res.getString("SUBJECT_CATEGORY")));
		
		BeaconStatement statement = new BeaconStatement();
		statement.setId(""+res.getInt("BEACON_STATEMENT_ID"));
		statement.setObject(object);
		statement.setPredicate(predicate);
		statement.setSubject(subject);
		return statement;
	}
	
	
	public static ArrayList<BeaconStatement> execute(
			List<String> s, List<String> sKeywords, List<String> sCategories, 
			String edgeLabel, String relation, 
			List<String> t, List<String> tKeywords, List<String> tCategories, 
			int offset, int size)
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = DBconnection.getConnection();

			ArrayList<BeaconStatement> list = new ArrayList<BeaconStatement>();

			ArrayList<Integer> relationIds = (relation == null || relation.equals("")) ? null : relationIds(edgeLabel, relation, con);
			ArrayList<Integer> sourceIds = (s == null || s.size() == 0) ? null : conceptIds(s, con);
			ArrayList<Integer> targetIds = (t == null || t.size() == 0) ? null : conceptIds(t, con);
			ArrayList<Integer> sCategoryIds = (sCategories == null || sCategories.size() == 0) ? null : categoryIds(sCategories, con);
			ArrayList<Integer> tCategoryIds = (tCategories == null || tCategories.size() == 0) ? null : categoryIds(tCategories, con);

			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(statementSQL(sourceIds, sKeywords, sCategoryIds, relationIds, targetIds, tKeywords, tCategoryIds));
			int i = 0;
			while (res.next() && i < offset+Math.min(size,Integer.MAX_VALUE-offset)) {
				if (i >= offset) {
					list.add(getStatement(res));
				}
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
