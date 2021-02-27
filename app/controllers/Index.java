package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Index extends Controller{
    public Result index() {
        return ok(views.html.index.render());
    }
    public Result main()   { return ok(views.html.welcome.render()); }
}
