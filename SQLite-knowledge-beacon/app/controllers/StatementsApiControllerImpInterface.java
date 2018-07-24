package controllers;

import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface StatementsApiControllerImpInterface {
    BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer size) throws Exception;

    List<BeaconStatement> getStatements( @NotNull List<String> s, String edgeLabel, String relation, List<String> t, List<String> keywords, List<String> categories, Integer size) throws Exception;

}
