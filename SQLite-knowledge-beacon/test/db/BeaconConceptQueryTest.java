package db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;

import apimodels.BeaconConcept;

public class BeaconConceptQueryTest {

	@Test
	public void testConceptSql() throws Exception {
		Connection c = DBconnection.getConnection();
		BeaconConcept concept = BeaconConceptQuery.getConcept(1, c);
		assertEquals(concept.getCategories().get(0),"metabolite");
		concept = BeaconConceptQuery.getConcept(2, c);
		assertEquals(concept.getCategories().get(0),"disease");
		concept = BeaconConceptQuery.getConcept(22, c);
		assertEquals(concept.getCategories().get(0),"protein");
	}


	@Test
	public void testExecute() throws Exception {
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("Keto");
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("metabolite");
		ArrayList<BeaconConcept> list = BeaconConceptQuery.execute(keywords, categories, 0, Integer.MAX_VALUE);
		assertEquals(1, list.size());
		keywords.add("methyl");
		list = BeaconConceptQuery.execute(keywords, categories, 1, Integer.MAX_VALUE);
		assertEquals(4, list.size());
	}

}
