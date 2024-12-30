package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public Student(){}
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private int id;
    private String name;
    private int age;
    private static List<Student> studentList = new ArrayList<>();

    static void addStudentData() {
        studentList.add(new Student(1,"karthick",23));
        studentList.add(new Student(2,"Surya",20));
        studentList.add(new Student(3,"Vicky",29));
        studentList.add(new Student(4,"Sham",12));
        studentList.add(new Student(5,"Saran",13));
        studentList.add(new Student(6,"Dhanvi",7));
    }
    static {
        addStudentData();
    }
    public static Student getStudentByName(String name) {
        for(Student student : studentList) {
            if(student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:\"" + name + "\"" +
                ", age=" + age +
                "}";
    }
}
