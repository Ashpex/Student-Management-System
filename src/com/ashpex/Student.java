package com.ashpex;

public class Student {
    String id;
    String name;
    String gender;
    String image;
    String address;
    String note;
    Float grade;

    public Student() {
    }

    public Student(String id, String name, String gender, String image, String address, String note, Float grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.address = address;
        this.note = note;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }
}
