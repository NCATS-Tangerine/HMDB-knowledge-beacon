package controllers;

import java.util.List;

import apimodels.BeaconConceptCategory;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconPredicate;
import db.BeaconConceptCategoryQuery;
import db.BeaconKnowledgeMapQuery;
import db.BeaconPredicateQuery;


public class MetadataApiControllerImp implements MetadataApiControllerImpInterface {
    @Override
    public List<BeaconConceptCategory> getConceptCategories() throws Exception {
    	return BeaconConceptCategoryQuery.execute();
    }

    @Override
    public List<BeaconKnowledgeMapStatement> getKnowledgeMap() throws Exception {
        return BeaconKnowledgeMapQuery.execute();
    }

    @Override
    public List<BeaconPredicate> getPredicates() throws Exception {
    	return BeaconPredicateQuery.execute();
    }

}
