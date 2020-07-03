package com.notalarim.recycler;


import java.util.List;

/**
 * induiduel webview projesidir.
 */


public class Person {

    public List<Person> person_list;
    private String name;
    private String age;
    private int photo_id;

    public Person(String name, int photo_id) {
        this.name = name;

        this.photo_id = photo_id;
    }

    public Person() {

    }

    public String getName() {
        return this.name;
    }

    public int getPhoto_id() {
        return this.photo_id;
    }


}
