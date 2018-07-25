package controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import apimodels.BeaconStatement;
import apimodels.BeaconStatementWithDetails;
import db.BeaconStatementDetailsQuery;
import db.BeaconStatementQuery;

public class StatementsApiControllerImp implements StatementsApiControllerImpInterface {
    @Override
    public BeaconStatementWithDetails getStatementDetails(String statementId, List<String> keywords, Integer size) throws Exception {
        return BeaconStatementDetailsQuery.execute(statementId, keywords, size==null?Integer.MAX_VALUE:size);
    }

    @Override
    public List<BeaconStatement> getStatements( @NotNull List<String> s, String edgeLabel, String relation, List<String> t, List<String> keywords, List<String> categories, Integer size) throws Exception {
    	return BeaconStatementQuery.execute(s, relation, t, keywords, categories, size==null?Integer.MAX_VALUE:size);
    }

}
