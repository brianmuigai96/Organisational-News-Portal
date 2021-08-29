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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getEmployee_count() == that.getEmployee_count() &&
                getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getEmployee_count(), getId());
    }

    public static void add(Department department) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO departments (name, description, employee_count) VALUES (:name, :description, :employeeCount)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(department)
                    .addParameter("employeeCount", department.getEmployee_count())
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex){
            System.out.println("Unable to add department" + ex);
        }
    }

    public static List<Department> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM departments";
            return con.createQuery(sql).executeAndFetch(Department.class);
        }
    }


}
