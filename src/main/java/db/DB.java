package db;
import org.sql2o.*;

public class DB {
//    public static String connectionString = ;
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-156-151-232.compute-1.amazonaws.com:5432/ddeqkep2860cfm", "rnzyzmakplavfa", "1f63071945e29f806677521eae751205396ddc533896683fc020dd30d99193f6");
        public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal", "brian", "1234");
}
