package controllers;

import com.google.inject.Inject;
import play.db.jpa.Transactional;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.PersonRepository;

import java.util.HashMap;
import java.util.Map;


public class People extends Controller {
    @Inject
    public PersonRepository personRepository;

    public Result jsonResponse(String field, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(field, data);
        return ok(Json.toJson(response)).as("application/json");
    }

    @Transactional
    public Result findAll() {
        return jsonResponse("people",Json.toJson(personRepository.findAll())).as("application/json");
    }

    @Transactional
    public Result findById(int id) {
        return jsonResponse("person",Json.toJson(personRepository.findById(id))).as("application/json");
    }

    @Transactional
    public Result create() {
        Person personRequest = Json.fromJson(request().body().asJson().findPath("person"), Person.class);
        personRequest.id = personRepository.add(personRequest).id;
        return jsonResponse("person",Json.toJson(personRequest)).as("application/json");
    }

    @Transactional
    public Result update(int id) {
        Person personRequest = Json.fromJson(request().body().asJson().findPath("person"), Person.class);
        personRequest.setId(id);
        personRepository.update(personRequest);
        return jsonResponse("person",Json.toJson(personRequest)).as("application/json");
    }

    @Transactional
    public Result delete(int id) {
        personRepository.delete(id);
        return ok("{}").as("application/json");
    }
}
