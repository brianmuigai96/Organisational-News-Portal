import com.google.gson.Gson;
import static spark.Spark.*;

import exceptions.ApiException;
import models.*;
import org.sql2o.Connection;
import com.google.gson.Gson;
import org.sql2o.Sql2o;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        Gson gson = new Gson();

        get("/", "application/json", (request, response) -> {
            return gson.toJson(Department.all());
        });


        get("/departments/:departmentId", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(Department.findById(departmentId));
        });
        get("/departments/:departmentId/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(Department.allDepartmentUsers(departmentId));
        });


        get("/departments/:departmentId/users/:userId/details", "application/json", (request, response) -> {
            int employeeId = Integer.parseInt(request.queryParams("userId"));

            User userToFind = User.findById(employeeId);

            if (userToFind == null){
                throw new ApiException(404, String.format("No user with id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(userToFind);
        });


        get("/users", "application/json", (request, response) -> {
            return gson.toJson(User.all());
        });


        get("/users/:userId/details", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));

            User userToFind = User.findById(userId);

            if (userToFind == null){
                throw new ApiException(404, String.format("No user with id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(User.findById(userId));
        });



    }
