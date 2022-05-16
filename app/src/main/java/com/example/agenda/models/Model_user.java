package com.example.agenda.models;

public class Model_user {
    private int id;
    private String name;
    private String last;
    private String phone;
    private String date;
    private String time;



    private String categoria;
    public Model_user() {
        this.id = 0;
        this.phone = "phone";
        this.name = "name";
        this.last = "last";
        this.categoria = "categoria";
        this.date = "date";
        this.time = "time";
    }
    public Model_user(int id, String phone, String name, String last, String date, String time, String categoria) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.last = last;
        this.date = date;
        this.time = time;
        this.categoria = categoria;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
