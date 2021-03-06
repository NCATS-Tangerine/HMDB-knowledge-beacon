package db;


import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;


import apimodels.BeaconConceptWithDetails;

public class BeaconConceptDetailsQueryTest {

	@Test
	public void testSynonymsSql() throws Exception {
		Connection c = DBconnection.getConnection();
		assertEquals(13,BeaconConceptDetailsQuery.synonyms(1,c).size());
	}


	@Test
	public void testExactMatches() throws Exception {
		Connection c = DBconnection.getConnection();
		assertEquals(8,BeaconConceptDetailsQuery.exactMatches(1,c).size());
	}


	@Test
	public void testGetConcept() throws Exception {
		String query = "HMDB:HMDB0000001";
		BeaconConceptWithDetails concept = BeaconConceptDetailsQuery.getConceptWithDetails(query);
		assertEquals(13,concept.getSynonyms().size());
		assertEquals(query,concept.getId());
	}
}
