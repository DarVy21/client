package Server.Entities;

import java.io.Serializable;

public class BasketEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private int idBasket;
    private int amount;
    private double price;
    private BookEntity book;
    private String name;

    public BasketEntity(){}

    public int getId() {
        return idBasket;
    }

    public void setId(int id) {
        this.idBasket = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = book.getName();
    }

    public String getName() {
        return book.getName();
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public BookEntity getBook() {
        return book;
    }
}