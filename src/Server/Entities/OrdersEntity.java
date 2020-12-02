package Server.Entities;


import java.io.Serializable;
import java.util.Objects;

public class OrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderNumber;
    private int totalAmount;
    private double totalPrice;
    private String status;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int idOrder) {
        this.orderNumber = idOrder;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdersEntity)) return false;
        OrdersEntity that = (OrdersEntity) o;
        return getOrderNumber() == that.getOrderNumber() &&
                Double.compare(that.getTotalPrice(), getTotalPrice()) == 0 &&
                getTotalAmount() == that.getTotalAmount() &&
                getStatus().equals(that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber(), getTotalPrice(), getTotalAmount(), getStatus());
    }
}