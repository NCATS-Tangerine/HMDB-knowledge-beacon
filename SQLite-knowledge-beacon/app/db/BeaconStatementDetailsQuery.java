package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import apimodels.BeaconStatementAnnotation;
import apimodels.BeaconStatementCitation;
import apimodels.BeaconStatementWithDetails;


/**
 * Query SQLite database for statement details.
 *
 */
public class BeaconStatementDetailsQuery {
	
	private static String statementSQL(int statementId){
		return "SELECT BEACON_STATEMENT_ID, IS_DEFINED_BY, PROVIDED_BY "
				+ "FROM BEACON_STATEMENT "
				+ "WHERE BEACON_STATEMENT_ID = "+statementId;
	}

	private static String qualifiersSQL(int statementId){
		return "SELECT BEACON_STATEMENT_ID, QUALIFIER "
				+ "FROM BEACON_STATEMENT_QUALIFIER "
				+ "WHERE BEACON_STATEMENT_ID = "+statementId;
	}
	
	
	private static String annotationSQL(int statementId){
		return "SELECT BEACON_STATEMENT_ID, TAG, VALUE "
				+ "FROM BEACON_STATEMENT_ANNOTATION "
				+ "WHERE BEACON_STATEMENT_ID = "+statementId;
	}
	
	
	private static String citationSQL(int statementId, List<String> keywords){
		return "SELECT BEACON_REFERENCE.ID, BEACON_REFERENCE.URI, BEACON_REFERENCE.NAME, BEACON_REFERENCE.EVIDENCE_TYPE, BEACON_REFERENCE.DATE "
				+ "FROM BEACON_STATEMENT_CITATION "
				+ "INNER JOIN BEACON_REFERENCE"
				+ "  ON BEACON_REFERENCE.BEACON_REFERENCE_ID = BEACON_STATEMENT_CITATION.BEACON_REFERENCE_ID "
				+ "WHERE BEACON_STATEMENT_CITATION.BEACON_STATEMENT_ID = "+statementId+keywordClause(keywords)
				+ " ORDER BY BEACON_STATEMENT_CITATION_ID";
	}
	
	private static String keywordClause( List<String> keywords){
		if (keywords == null || keywords.size() == 0 || keywords.size() == 1 && keywords.get(0).length() == 0)
			return "";
		StringBuffer sb = new StringBuffer(" AND (");
		boolean first = true;
		for (String keyword : keywords){
			if (!first) sb.append(" OR ");
			sb.append("BEACON_REFERENCE.NAME LIKE '%"+keyword+"%'");
			first = false;
		}
		sb.append(")");
		return sb.toString();
	}
	
	private static List<String> getQualifiers(int statementId, Connection con) throws SQLException {
		ArrayList<String> qualifiers = new ArrayList<String>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(qualifiersSQL(statementId));
		while(res.next()){
			qualifiers.add(res.getString("QUALIFIER"));
		}
		stmt.close();
		return qualifiers;
	}
	
	private static List<BeaconStatementAnnotation> getAnnotations(int statementId, Connection con) throws SQLException {
		ArrayList<BeaconStatementAnnotation> annotations = new ArrayList<BeaconStatementAnnotation>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(annotationSQL(statementId));
		while(res.next()){
			BeaconStatementAnnotation annotation = new BeaconStatementAnnotation();
			annotation.setTag(res.getString("TAG"));
			annotation.setValue(res.getString("VALUE"));
			annotations.add(annotation);
		}
		stmt.close();
		return annotations;
	}
	
	private static List<BeaconStatementCitation> getCitations(int statementId, List<String> keywords, int offset, int size, Connection con) throws SQLException {
		ArrayList<BeaconStatementCitation> citations = new ArrayList<BeaconStatementCitation>();
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(citationSQL(statementId, keywords));
		int i = 0;
		while(res.next() && i < offset+Math.min(size,Integer.MAX_VALUE-offset)){
			if (i >= offset) {
				BeaconStatementCitation citation = new BeaconStatementCitation();
				citation.setId(res.getString("ID"));
				citation.setUri(res.getString("URI"));
				citation.setName(res.getString("NAME"));
				citation.setEvidenceType(res.getString("EVIDENCE_TYPE"));
				citation.setDate(res.getString("DATE"));
				citations.add(citation);
			}
			i = i + 1;
		}
		stmt.close();
		return citations;
	}
	
	public static BeaconStatementWithDetails execute(String statementId, List<String> keywords, int offset, int size)
	throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = DBconnection.getConnection();
			BeaconStatementWithDetails details = new BeaconStatementWithDetails();
			details.setId(statementId);
			try {
				int id = Integer.parseInt(statementId);
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(statementSQL(id));
				if (res.next()){
					details.setIsDefinedBy(res.getString("IS_DEFINED_BY"));
					details.setProvidedBy(res.getString("PROVIDED_BY"));
					int beaconStatementId = res.getInt("BEACON_STATEMENT_ID");
					details.setQualifiers(getQualifiers(beaconStatementId, con));
					details.setAnnotation(getAnnotations(beaconStatementId, con));
					details.setEvidence(getCitations(beaconStatementId, keywords, offset, size, con));
				}
				stmt.close();
			} catch (NumberFormatException e){
				
			}
			return details;
		} finally {
			if (con != null)
				con.close();
		}
	}


}
