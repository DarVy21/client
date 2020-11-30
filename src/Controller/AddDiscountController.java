package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDiscountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField promocod;

    @FXML
    private TextField discountSize;

    @FXML
    private TextField clientId;


    @FXML
    private ComboBox<String> client;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            addDiscount();
        });

        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminDiscountWindow.fxml");
        });
    }

    private void addDiscount() {
        String clientIDTF=clientId.getText().trim();
        String discountTF =discountSize.getText().trim();
        String promocodTF=promocod.getText().trim();
        if (Integer.parseInt(discountTF)<=0)
            MessageLabel.setText("Ошибка, повторите ввод.");
        else{
            String message="Discount,addDiscount,"+clientIDTF+","+promocodTF+","+discountTF;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(message.equals("success")){
                MessageLabel.setText("Промокод добавлен.");
            }
            else MessageLabel.setText("Ошибка.");

        }
    }


    public void openNewScene(String window) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}