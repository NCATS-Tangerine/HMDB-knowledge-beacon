package controllers;

import apimodels.BeaconConcept;
import apimodels.BeaconConceptWithDetails;
import apimodels.ExactMatchResponse;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

public class ConceptsApiControllerImp implements ConceptsApiControllerImpInterface {
    @Override
    public BeaconConceptWithDetails getConceptDetails(String conceptId) throws Exception {
        //Do your magic!!!
        return new BeaconConceptWithDetails();
    }

    @Override
    public List<BeaconConcept> getConcepts( @NotNull List<String> keywords, List<String> categories, Integer size) throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconConcept>();
    }

    @Override
    public List<ExactMatchResponse> getExactMatchesToConceptList( @NotNull List<String> c) throws Exception {
        //Do your magic!!!
        return new ArrayList<ExactMatchResponse>();
    }

}
