package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderDiscountController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OkButton;

    @FXML
    private Button usePromocod;

    @FXML
    private Label totalPrice;
    @FXML
    private Label invalid;

    @FXML
    private TextField promocodField;

    @FXML
    void initialize() {

        getTotalPrice();
        usePromocod.setOnAction(actionEvent -> {
            addDiscount();
        });

        OkButton.setOnAction(actionEvent -> {
            OkButton.getScene().getWindow().hide();
        });
    }

    private void getTotalPrice() {
        String message = "Order,showPrice," + Client.getId_user();
        try {
            Client.os.writeObject(message);
            message = (String) Client.is.readObject();
            totalPrice.setText(message);

        }catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
         }
    }

    private void addDiscount(){
        String promocodFieldTF=promocodField.getText().trim();
        String message = "Order,discountPrice," + Client.getId_user()+ "," +promocodFieldTF;
        if(promocodFieldTF.length() !=0){
            try {
                Client.os.writeObject(message);
                message = (String) Client.is.readObject();
                if(message.equals("fail")){
                    invalid.setText("Промокод не применился");
                }else {
                    totalPrice.setText(message);
                }
            } catch (IOException  | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}


