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
		s.add("hmdb:HMDB0000001");
		s.add("hmdb:HMDB0000008");
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> k = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		c.add("disease");

		ArrayList<BeaconStatement> list = BeaconStatementQuery.execute(s,relation, t, k, c, 6);
		assertEquals(6, list.size());
		assertEquals("hmdb:HMDB0000001", list.get(0).getSubject().getId());
		assertEquals("hmdb:HMDB0000008", list.get(5).getSubject().getId());

	}
}
