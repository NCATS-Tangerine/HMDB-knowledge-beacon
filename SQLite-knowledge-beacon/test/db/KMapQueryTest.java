package db;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class KMapQueryTest{

	@Test
	public void testPrefixes() throws Exception {
		Connection c = DBconnection.getConnection();
		Map<String, ArrayList<String>> prefixes = BeaconKnowledgeMapQuery.getPrefixes(c);
		assertEquals(prefixes.size(), 6);
		for (String cat : prefixes.keySet()) {
			System.out.print(cat+"\t");
			for (String prefix : prefixes.get(cat)) {
				System.out.print(prefix + ": ");
			}
			System.out.println();
		}
	}
}
