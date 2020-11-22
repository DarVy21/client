package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookEntity{

    private int id_book;
    private String type;
    private String name;
    private String author;
    private int amount;
    private double price;

    public BookEntity(){}

    public BookEntity(int idBook, String type, String name, String author, int amount, double price){
        this.id_book=idBook;
        this.type=type;
        this.name=name;
        this.author=author;
        this.amount=amount;
        this.price=price;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int idbook) {
        this.id_book = idbook;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
