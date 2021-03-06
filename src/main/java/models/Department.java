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

    public void setName(String name) {
        this.name = name;
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

    public void addUserToDepartment(User user, Department department){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO departments_users(deptid, userid) VALUES (:deptId, :userId)";
            con.createQuery(sql)
                    .addParameter("deptId", department.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
            user.setDepartment(department.getName());
            this.increaseEmployeeCount();
        } catch (Sql2oException ex){
            System.out.println("Unable to add user to department: " + ex);
        }
    }

    public static Department findById(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT FROM departments WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    public static List<User> allDepartmentUsers(int departmentId){
        List<User> users = new ArrayList<>();
        String joinQuery = "SELECT FROM departments_users WHERE departmentid=:deptId";
        try(Connection con = DB.sql2o.open()){
            List<Integer> userIds = con.createQuery(joinQuery)
                    .addParameter("deptId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId:userIds){
                String sql = "SELECT FROM users WHERE id=:id";
                users.add(
                        con.createQuery(sql)
                                .addParameter("id", userId)
                                .executeAndFetchFirst(User.class)
                );
            }
        } catch (Sql2oException ex){
            System.out.println("Unable to fetch all department users: " + ex);
        }
        return users;
    }

    public void updateUserCount(Department department) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "UPDATE departments SET employee_count= :employeeCount WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("employeeCount", department.getEmployee_count())
                    .addParameter("id", department.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to update departments: " + ex);
        }
    }

    public static void deleteDepartmentById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM departments WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete department by ID: " + ex);
        }
    }

    public static void deleteEmployeeFromDepartment(Department department, User user) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE from departments_users WHERE deptid = :deptId AND userId = :userId";
            conn.createQuery(sql)
                    .addParameter("deptId", department.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
            user.setDepartment("None");
            department.reduceEmployeeCount();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete from dept_users: " + ex);
        }
    }

    public static void clearAll() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM departments";
            conn.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete all departments: " + ex);
        }
    }

}
