package db;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import apimodels.BeaconStatementWithDetails;

public class BeaconStatementDetailsQueryTest {

	@Test
	public void testDetails() throws Exception {
		BeaconStatementWithDetails details = BeaconStatementDetailsQuery.execute("1", null, 0, Integer.MAX_VALUE);
		assertEquals("translator@broadinstitute.org", details.getIsDefinedBy());
	}

	@Test
	public void testCitations() throws Exception {
		BeaconStatementWithDetails details = BeaconStatementDetailsQuery.execute("1", null, 8, Integer.MAX_VALUE);
		assertEquals(16, details.getEvidence().size());
		details = BeaconStatementDetailsQuery.execute("1", null, 0, 5);
		assertEquals(5, details.getEvidence().size());
		details = BeaconStatementDetailsQuery.execute("1", Arrays.asList(new String[]{"fluid"}), 4, 50);
		assertEquals(12, details.getEvidence().size());
	}



}
