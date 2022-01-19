package db;
import org.sql2o.*;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-225-187-177.compute-1.amazonaws.com:5432/dfe1jsmaprbn2e?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "yyaicgxoththsd", "681c422ccafcf464a70a233d17a123770d44020d04b88e7589a2f8d9b57f2c19");
//        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal", "brian", "1234");
}
