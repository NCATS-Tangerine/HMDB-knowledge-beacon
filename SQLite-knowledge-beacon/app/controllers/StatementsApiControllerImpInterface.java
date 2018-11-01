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
    BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer offset, Integer size) throws Exception;

    List<BeaconStatement> getStatements(List<String> s, List<String> sKeywords, List<String> sCategories, String edgeLabel, String relation, List<String> t, List<String> tKeywords, List<String> tCategories, Integer offset, Integer size) throws Exception;

}
