package controllers;

import com.google.inject.Inject;
import play.db.jpa.Transactional;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.GuitarRepository;

import java.util.HashMap;
import java.util.Map;


public class GuitarController extends Controller {

    @Inject
    public GuitarRepository guitarRepository;

    public Result jsonResponse(String field, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(field, data);

        return ok(Json.toJson(response)).as("application/json");
    }

    @Transactional
    public Result findAll() {
        return jsonResponse("guitars",Json.toJson(guitarRepository.findAll())).as("application/json");
    }

    @Transactional
    public Result findById(int id) {
        return jsonResponse("guitar",Json.toJson(guitarRepository.findById(id))).as("application/json");
    }

    @Transactional
    public Result create() {
        Guitar guitarRequest = Json.fromJson(request().body().asJson().findPath("guitar"), Guitar.class);

        guitarRequest.id = guitarRepository.add(guitarRequest).id;
        System.out.println(guitarRequest.getId());
        return jsonResponse("guitar",Json.toJson(guitarRequest)).as("application/json");
    }

    @Transactional
    public Result update(int id) {
        Guitar guitarRequest = Json.fromJson(request().body().asJson().findPath("guitar"), Guitar.class);

        guitarRequest.setId(id);

        guitarRepository.update(guitarRequest);

        return jsonResponse("guitar",Json.toJson(guitarRequest)).as("application/json");
    }

    @Transactional
    public Result delete(int id) {
        guitarRepository.delete(id);
        return ok("{}").as("application/json");
    }
}
