package controllers;

import apimodels.BeaconConcept;
import apimodels.BeaconConceptWithDetails;
import apimodels.ExactMatchResponse;

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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

public class ConceptsApiController extends Controller {

    private final ConceptsApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private ConceptsApiController(Configuration configuration, ConceptsApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result getConceptDetails(String conceptId) throws Exception {
        BeaconConceptWithDetails obj = imp.getConceptDetails(conceptId);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            SwaggerUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getConcepts() throws Exception {
        String[] keywordsArray = request().queryString().get("keywords");
        if (keywordsArray == null) {
            throw new IllegalArgumentException("'keywords' parameter is required");
        }
        List<String> keywordsList = SwaggerUtils.parametersToList("multi", keywordsArray);
        List<String> keywords = new ArrayList<String>();
        for (String curParam : keywordsList) {
            //noinspection UseBulkOperation
            keywords.add(curParam);
        }
        String[] categoriesArray = request().queryString().get("categories");
        List<String> categoriesList = SwaggerUtils.parametersToList("multi", categoriesArray);
        List<String> categories = new ArrayList<String>();
        for (String curParam : categoriesList) {
            //noinspection UseBulkOperation
            categories.add(curParam);
        }
        String valuesize = request().getQueryString("size");
        Integer size;
        if (valuesize != null) {
            size = Integer.parseInt(valuesize);
        } else {
            size = null;
        }
        List<BeaconConcept> obj = imp.getConcepts(keywords, categories, size);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (BeaconConcept curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getExactMatchesToConceptList() throws Exception {
        String[] cArray = request().queryString().get("c");
        if (cArray == null) {
            throw new IllegalArgumentException("'c' parameter is required");
        }
        List<String> cList = SwaggerUtils.parametersToList("multi", cArray);
        List<String> c = new ArrayList<String>();
        for (String curParam : cList) {
            //noinspection UseBulkOperation
            c.add(curParam);
        }
        List<ExactMatchResponse> obj = imp.getExactMatchesToConceptList(c);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (ExactMatchResponse curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
