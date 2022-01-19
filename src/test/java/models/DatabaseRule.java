package models;
import org.sql2o.*;
import db.DB;
import org.junit.rules.ExternalResource;

public class DatabaseRule extends ExternalResource{
    @Override
    public void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://ec2-54-225-187-177.compute-1.amazonaws.com:5432/dfe1jsmaprbn2e", "yyaicgxoththsd", "681c422ccafcf464a70a233d17a123770d44020d04b88e7589a2f8d9b57f2c19");}


    @Override
    public void after(){
        try(Connection conn = DB.sql2o.open()){
            String deleteUserQuery = "DELETE FROM users";
            String deleteDepartmentsQuery = "DELETE FROM departments";
            String deleteNewsQuery = "DELETE FROM news";
            conn.createQuery(deleteUserQuery).executeUpdate();
            conn.createQuery(deleteDepartmentsQuery).executeUpdate();
            conn.createQuery(deleteNewsQuery).executeUpdate();
        }
    }
}
