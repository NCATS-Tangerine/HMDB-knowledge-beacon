package controllers;

import javax.inject.*;
import play.mvc.*;

public class ApiDocController extends Controller {

    @Inject
    private ApiDocController() {
    }

    public Result api() {
        return redirect("/hmdb-knowledge-beacon/assets/lib/swagger-ui/index.html?url=/hmdb-knowledge-beacon/assets/swagger.json");
    }
}
