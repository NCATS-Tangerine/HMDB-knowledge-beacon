package db;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import org.junit.Test;

import apimodels.ExactMatchResponse;

public class ExactMatchQueryTest {

	@Test
	public void testWithinDomain() throws Exception {
		Connection c = DBconnection.getConnection();
		ExactMatchResponse response = ExactMatchQuery.exactMatch("hmdb:HMDB0000014", c);
		assertEquals(response.isWithinDomain(),true);
		response = ExactMatchQuery.exactMatch("HMDB0000014", c);
		assertEquals(response.isWithinDomain(),false);
	}


	@Test
	public void testExactMatchQuery() throws Exception {
		Connection c = DBconnection.getConnection();
		ExactMatchResponse response = ExactMatchQuery.exactMatch("hmdb:HMDB0000014", c);
		assertEquals(response.getHasExactMatches().get(0),"hmdb:HMDB0000014");
		response = ExactMatchQuery.exactMatch("chebi:50599", c);
		assertEquals(response.getHasExactMatches().get(0),"hmdb:HMDB0000001");
	}


}
