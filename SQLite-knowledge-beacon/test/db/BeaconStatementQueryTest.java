package db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import apimodels.BeaconStatement;


public class BeaconStatementQueryTest {

	@Test
	public void testCategories() throws Exception {

		ArrayList<String> s = new ArrayList<String>();
		String relation = "related to";
		s.add("HMDB:HMDB0000001");
		s.add("HMDB:HMDB0000008");
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> k = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		c.add("disease");

		ArrayList<BeaconStatement> list = BeaconStatementQuery.execute(s, null, null, "", relation, t, k, c, 8, 6);
		assertEquals(6, list.size());
		assertEquals("HMDB:HMDB0000001", list.get(0).getSubject().getId());
		assertEquals("HMDB:HMDB0000008", list.get(5).getSubject().getId());

	}
	
	@Test
	public void testKeywords() throws Exception {

		ArrayList<String> s = new ArrayList<String>();
		String edge_label = "related_to";
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> k = new ArrayList<String>();
		k.add("Disease");
		ArrayList<String> c = new ArrayList<String>();


		ArrayList<BeaconStatement> list = BeaconStatementQuery.execute(s, null, null, edge_label, "", t, k, c, 0, Integer.MAX_VALUE);
		assertEquals(8, list.size());
		assertEquals("Alzheimer's disease", list.get(0).getObject().getName());
		assertEquals("Kidney disease", list.get(1).getObject().getName());
		assertEquals("HMDB:HMDB0000001", list.get(0).getSubject().getId());

	}
}
