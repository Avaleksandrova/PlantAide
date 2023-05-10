package com.example.plantaide.model;

public class Course {

    int id;
    String title, exciter, sign, color, text;

    public Course(int id, String title, String exciter, String sign, String color, String text) {
        this.id = id;
        this.title = title;
        this.exciter = exciter;
        this.sign = sign;
        this.color = color;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExciter() {
        return exciter;
    }

    public void setExciter(String exciter) {
        this.exciter = exciter;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
