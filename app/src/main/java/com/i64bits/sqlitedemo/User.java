package com.i64bits.sqlitedemo;

public class User
{
    private long id = -1;

    private String name = "Kiran";
    private Integer age = 22;
    private String location = "Pune";

    public User()
    {

    }

    public User(String name, Integer age, String location)
    {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }
}
