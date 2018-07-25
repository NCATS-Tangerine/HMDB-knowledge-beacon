package controllers;

import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-07-24T21:10:45.082Z")

public class StatementsApiControllerImp implements StatementsApiControllerImpInterface {
    @Override
    public BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer size) throws Exception {
        //Do your magic!!!
        return new BeaconStatementWithDetails();
    }

    @Override
    public List<BeaconStatement> getStatements( @NotNull List<String> s, String edgeLabel, String relation, List<String> t, List<String> keywords, List<String> categories, Integer size) throws Exception {
        //Do your magic!!!
        return new ArrayList<BeaconStatement>();
    }

}
