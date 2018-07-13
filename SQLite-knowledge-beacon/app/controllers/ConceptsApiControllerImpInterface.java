package controllers;

import apimodels.BeaconConcept;
import apimodels.BeaconConceptWithDetails;
import apimodels.ExactMatchResponse;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface ConceptsApiControllerImpInterface {
    BeaconConceptWithDetails getConceptDetails(String conceptId) throws Exception;

    List<BeaconConcept> getConcepts( @NotNull List<String> keywords, List<String> categories, Integer size) throws Exception;

    List<ExactMatchResponse> getExactMatchesToConceptList( @NotNull List<String> c) throws Exception;

}
