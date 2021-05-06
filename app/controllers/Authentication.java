package controllers;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import java.util.HashMap;
import java.util.Map;

public class Authentication extends Controller {

    public Result jsonResponse(String field, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(field, data);
        return ok(Json.toJson(response)).as("application/json");
    }

    public static class Login {
        public String email;
        public String password;
    }

    public static class User {
        public String email = "email";
        public String password = "password";
    }

    public static Result createErrorResponse(String key, Object obj) {
        Map<String, Object> response = new HashMap<>();
        response.put(key, obj);
        return Results.status(422, Json.toJson(response));
    }
// create container for our auth data
    @Transactional
    public Result login() {
      Login loginRequest = Json.fromJson(request().body().asJson(), Login.class);
      if (loginRequest.email.isEmpty()) {
          String errorMessage = "Please Login";
          return createErrorResponse("errors", errorMessage);
      }
      if (loginRequest.password.isEmpty()) {
          String errorMessage = "Please Enter Password";
            return createErrorResponse("errors", errorMessage);
      }

        User user = new User();

       if (loginRequest.password.equals(user.password) && loginRequest.email.equals(user.email)) {
           session("email", loginRequest.email);
           System.out.println(user.password + " " + loginRequest.password );
           return jsonResponse("success", user);
       }
       return unauthorized();
    }

    public Result logout() {
        session().clear();
        return ok("{}").as("application/json");
    }

    public Result checkSession() {
        String userEmail = session("email");
        if (userEmail == null) {
            return unauthorized();
        } else {
            return jsonResponse("success", userEmail);
        }
    }
}
