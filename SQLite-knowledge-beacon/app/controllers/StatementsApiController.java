package controllers;

import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;

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

public class StatementsApiController extends Controller {

    private final StatementsApiControllerImpInterface imp;
    private final ObjectMapper mapper;
    private final Configuration configuration;

    @Inject
    private StatementsApiController(Configuration configuration, StatementsApiControllerImpInterface imp) {
        this.imp = imp;
        mapper = new ObjectMapper();
        this.configuration = configuration;
    }


    @ApiAction
    public Result getStatementDetails(String statementId) throws Exception {
        String[] keywordsArray = request().queryString().get("keywords");
        List<String> keywordsList = SwaggerUtils.parametersToList("multi", keywordsArray);
        List<String> keywords = new ArrayList<String>();
        for (String curParam : keywordsList) {
            //noinspection UseBulkOperation
            keywords.add(curParam);
        }
        String valueoffset = request().getQueryString("offset");
        Integer offset;
        if (valueoffset != null) {
            offset = Integer.parseInt(valueoffset);
        } else {
            offset = null;
        }
        String valuesize = request().getQueryString("size");
        Integer size;
        if (valuesize != null) {
            size = Integer.parseInt(valuesize);
        } else {
            size = null;
        }
        BeaconStatementWithDetails obj = imp.getStatementDetails(statementId, keywords, offset, size);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            SwaggerUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getStatements() throws Exception {
        String[] sArray = request().queryString().get("s");
        List<String> sList = SwaggerUtils.parametersToList("multi", sArray);
        List<String> s = new ArrayList<String>();
        for (String curParam : sList) {
            //noinspection UseBulkOperation
            s.add(curParam);
        }
        String[] sKeywordsArray = request().queryString().get("s_keywords");
        List<String> sKeywordsList = SwaggerUtils.parametersToList("multi", sKeywordsArray);
        List<String> sKeywords = new ArrayList<String>();
        for (String curParam : sKeywordsList) {
            //noinspection UseBulkOperation
            sKeywords.add(curParam);
        }
        String[] sCategoriesArray = request().queryString().get("s_categories");
        List<String> sCategoriesList = SwaggerUtils.parametersToList("multi", sCategoriesArray);
        List<String> sCategories = new ArrayList<String>();
        for (String curParam : sCategoriesList) {
            //noinspection UseBulkOperation
            sCategories.add(curParam);
        }
        String valueedgeLabel = request().getQueryString("edge_label");
        String edgeLabel;
        if (valueedgeLabel != null) {
            edgeLabel = valueedgeLabel;
        } else {
            edgeLabel = null;
        }
        String valuerelation = request().getQueryString("relation");
        String relation;
        if (valuerelation != null) {
            relation = valuerelation;
        } else {
            relation = null;
        }
        String[] tArray = request().queryString().get("t");
        List<String> tList = SwaggerUtils.parametersToList("multi", tArray);
        List<String> t = new ArrayList<String>();
        for (String curParam : tList) {
            //noinspection UseBulkOperation
            t.add(curParam);
        }
        String[] tKeywordsArray = request().queryString().get("t_keywords");
        List<String> tKeywordsList = SwaggerUtils.parametersToList("multi", tKeywordsArray);
        List<String> tKeywords = new ArrayList<String>();
        for (String curParam : tKeywordsList) {
            //noinspection UseBulkOperation
            tKeywords.add(curParam);
        }
        String[] tCategoriesArray = request().queryString().get("t_categories");
        List<String> tCategoriesList = SwaggerUtils.parametersToList("multi", tCategoriesArray);
        List<String> tCategories = new ArrayList<String>();
        for (String curParam : tCategoriesList) {
            //noinspection UseBulkOperation
            tCategories.add(curParam);
        }
        String valueoffset = request().getQueryString("offset");
        Integer offset;
        if (valueoffset != null) {
            offset = Integer.parseInt(valueoffset);
        } else {
            offset = null;
        }
        String valuesize = request().getQueryString("size");
        Integer size;
        if (valuesize != null) {
            size = Integer.parseInt(valuesize);
        } else {
            size = null;
        }
        List<BeaconStatement> obj = imp.getStatements(s, sKeywords, sCategories, edgeLabel, relation, t, tKeywords, tCategories, offset, size);
        if (configuration.getBoolean("useOutputBeanValidation")) {
            for (BeaconStatement curItem : obj) {
                SwaggerUtils.validate(curItem);
            }
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }
}
