import com.google.inject.AbstractModule;

import controllers.*;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConceptsApiControllerImpInterface.class).to(ConceptsApiControllerImp.class);
        bind(MetadataApiControllerImpInterface.class).to(MetadataApiControllerImp.class);
        bind(StatementsApiControllerImpInterface.class).to(StatementsApiControllerImp.class);
    }
}