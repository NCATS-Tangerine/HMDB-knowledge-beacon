package controllers;

import apimodels.BeaconConceptCategory;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconPredicate;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

public class MetadataApiControllerImp implements MetadataApiControllerImpInterface {
    @Override
    public List<BeaconConceptCategory> getConceptCategories() throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconConceptCategory>();
    }

    @Override
    public List<BeaconKnowledgeMapStatement> getKnowledgeMap() throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconKnowledgeMapStatement>();
    }

    @Override
    public List<BeaconPredicate> getPredicates() throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconPredicate>();
    }

}
