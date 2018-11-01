package controllers;

import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-11-01T22:26:35.485Z")

public class StatementsApiControllerImp implements StatementsApiControllerImpInterface {
    @Override
    public BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer offset, Integer size) throws Exception {
        //Do your magic!!!
        return new BeaconStatementWithDetails();
    }

    @Override
    public List<BeaconStatement> getStatements(List<String> s, List<String> sKeywords, List<String> sCategories, String edgeLabel, String relation, List<String> t, List<String> tKeywords, List<String> tCategories, Integer offset, Integer size) throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconStatement>();
    }

}
