package models;

import db.DB;
import org.sql2o.Sql2oException;

import java.util.Objects;
import models.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public class Department {
    private String name;
    private String description;
    private int employee_count;
    private int id;

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployee_count() {
        return employee_count;
    }

    public void setEmployee_count(int employee_count) {
        this.employee_count = employee_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int increaseEmployeeCount(){
        return this.employee_count++;
    }

    public int reduceEmployeeCount(){
        return this.employee_count--;
    }



}
