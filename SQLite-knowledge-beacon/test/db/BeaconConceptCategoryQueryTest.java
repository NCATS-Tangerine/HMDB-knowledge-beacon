package db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import apimodels.BeaconConceptCategory;

public class BeaconConceptCategoryQueryTest {
	
	@Test
	public void testCategories() throws Exception {

		ArrayList<BeaconConceptCategory> list = BeaconConceptCategoryQuery.execute();
		assertEquals(6, list.size());
		assertEquals("metabolite",list.get(0).getCategory());
		assertEquals("disease",list.get(1).getCategory());
		assertEquals("pathway",list.get(2).getCategory());
		assertEquals("protein",list.get(5).getCategory());		
	}
}
