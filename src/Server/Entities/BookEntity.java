package Server.Entities;

import java.io.Serializable;
import java.util.Objects;


public class BookEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_book;
    private String type;
    private String name;
    private String author;
    private int amount;
    private double price;

    public BookEntity(){}

    public BookEntity(int idBook, String name, String author, String type, double price, int amount){
        this.id_book=idBook;
        this.type=type;
        this.name=name;
        this.author=author;
        this.amount=amount;
        this.price=price;
    }

    public BookEntity( String name, String author, String type, double price, int amount){
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        BookEntity that = (BookEntity) o;
        return getId_book() == that.getId_book() &&
                getAmount() == that.getAmount() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                getName().equals(that.getName()) &&
                getAuthor().equals(that.getAuthor()) &&
                getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_book(), getName(), getAuthor(), getType(), getAmount(), getPrice());
    }
}
