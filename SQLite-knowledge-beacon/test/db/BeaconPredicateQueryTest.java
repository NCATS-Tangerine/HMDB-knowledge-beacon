package db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import apimodels.BeaconPredicate;

public class BeaconPredicateQueryTest {

	@Test
	public void testPredicates() throws Exception {
		ArrayList<BeaconPredicate> list = BeaconPredicateQuery.execute();
		assertEquals(4, list.size());
		assertEquals("related_to",list.get(0).getEdgeLabel());
		assertEquals("interacts_with",list.get(3).getEdgeLabel());
	}
}



