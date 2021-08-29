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
}
