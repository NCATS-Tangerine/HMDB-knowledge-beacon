package controllers;

import apimodels.BeaconConceptCategory;
import apimodels.BeaconKnowledgeMapStatement;
import apimodels.BeaconPredicate;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import java.io.File;
import swagger.SwaggerUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.validation.constraints.*;
import play.Configuration;

import swagger.SwaggerUtils.ApiAction;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-11-01T22:26:35.485Z")

public class MetadataApiController extends Controller {

    private final MetadataApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private MetadataApiController(Configuration configuration, MetadataApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result getConceptCategories() throws Exception {
        List<BeaconConceptCategory> obj = imp.getConceptCategories();
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (BeaconConceptCategory curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getKnowledgeMap() throws Exception {
        List<BeaconKnowledgeMapStatement> obj = imp.getKnowledgeMap();
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (BeaconKnowledgeMapStatement curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getPredicates() throws Exception {
        List<BeaconPredicate> obj = imp.getPredicates();
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (BeaconPredicate curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
