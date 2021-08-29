package models;

import db.DB;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String position;
    private String role;
    private String department;
    public User(String name, String position, String role, String department) {
        this.name = name;
        this.position = position;
        this.role = role;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getDepartment(), user.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getPosition(), getRole(), getDepartment());
    }

    public static void add(User user){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO users (name, position, role, department) VALUES (:name, :position, :role, :department)";
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex){
            System.out.println("Unable to add user to database: " + ex);
        }
    }
    public static List<User> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM users";
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public static User findById(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM users WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    public static void deleteById(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM users WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete user by id: " + ex);
        }
    }

    public static void deleteAll(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM users";
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete all users: " + ex);
        }
    }

}
