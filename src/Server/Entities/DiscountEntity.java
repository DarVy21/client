package Server.Entities;

import java.io.Serializable;

public class DiscountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_discount;

    private int user_id;
    private String promocod;
    private int discountSize;



    public DiscountEntity(){};

    public DiscountEntity(int id_discount, int user_id, String promocod, int discountSize) {
        this.id_discount = id_discount;
        this.user_id = user_id;
        this.promocod = promocod;
        this.discountSize = discountSize;
    }
    public DiscountEntity( int user_id, String promocod, int discountSize) {
        this.user_id = user_id;
        this.promocod = promocod;
        this.discountSize = discountSize;
    }


    public int getId_discount() {
        return id_discount;
    }

    public void setId_discount(int id_discount) {
        this.id_discount = id_discount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPromocod() {
        return promocod;
    }

    public void setPromocod(String promocod) {
        this.promocod = promocod;
    }

    public int getDiscountSize() {
        return discountSize;
    }

    public void setDiscountSize(int discountSize) {
        this.discountSize = discountSize;
    }
}
