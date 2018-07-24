package controllers;

import apimodels.BeaconConceptCategory;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconPredicate;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface MetadataApiControllerImpInterface {
    List<BeaconConceptCategory> getConceptCategories() throws Exception;

    List<BeaconKnowledgeMapStatement> getKnowledgeMap() throws Exception;

    List<BeaconPredicate> getPredicates() throws Exception;

}
