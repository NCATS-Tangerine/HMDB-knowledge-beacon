package controllers;

import java.util.List;
import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;
import db.BeaconStatementDetailsQuery;
import db.BeaconStatementQuery;

public class StatementsApiControllerImp implements StatementsApiControllerImpInterface {
    @Override
    public BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer offset, Integer size) throws Exception {
        return BeaconStatementDetailsQuery.execute(statementId, keywords, size==null?Integer.MAX_VALUE:size);
    }

    @Override
    public List<BeaconStatement> getStatements(List<String> s, List<String> sKeywords, List<String> sCategories, String edgeLabel, String relation, List<String> t, List<String> tKeywords, List<String> tCategories, Integer offset, Integer size) throws Exception {
    	return BeaconStatementQuery.execute(s, relation, t, sKeywords, sCategories, size==null?Integer.MAX_VALUE:size);
    }

}
