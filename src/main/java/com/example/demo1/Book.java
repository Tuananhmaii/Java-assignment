package com.example.demo1;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = -6500665823330706018L;

    private int id;
    private String name;
    private String genre;
    private String author;
    private int quantity;

    public Book(int id, String name, String genre, String author, int quantity) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.quantity = quantity;
    }

    public Book(){

    }

    public String toString(){
        return id + ";" + name + ";"+ genre+ ";" + author+ ";" +quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
