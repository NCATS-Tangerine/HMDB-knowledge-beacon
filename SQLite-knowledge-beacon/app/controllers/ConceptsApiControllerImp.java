package controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import apimodels.BeaconConcept;
import apimodels.BeaconConceptWithDetails;
import apimodels.ExactMatchResponse;
import db.BeaconConceptDetailsQuery;
import db.BeaconConceptQuery;
import db.ExactMatchQuery;


public class ConceptsApiControllerImp implements ConceptsApiControllerImpInterface {
    @Override
    public BeaconConceptWithDetails getConceptDetails(String conceptId) throws Exception {
        return BeaconConceptDetailsQuery.getConceptWithDetails(conceptId);
    }

    @Override
    public List<BeaconConcept> getConcepts(List<String> keywords, List<String> categories, Integer offset, Integer size) throws Exception {
        return BeaconConceptQuery.execute(keywords, categories, offset==null?0:offset, size==null?Integer.MAX_VALUE:size);
    }

    @Override
    public List<ExactMatchResponse> getExactMatchesToConceptList( @NotNull List<String> c) throws Exception {
        return ExactMatchQuery.execute(c);
    }

}
