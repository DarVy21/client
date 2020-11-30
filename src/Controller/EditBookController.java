package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField name;

    @FXML
    private TextField author;

    @FXML
    private TextField price;

    @FXML
    private TextField amount;

    @FXML
    private Button editButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        String message = "";
        try {
            message = (String) Client.is.readObject();
            String[] info=message.split(",",5);
            name.setText(info[0]);
            author.setText(info[1]);
            type.setValue(info[2]);
            amount.setText(info[3]);
            price.setText(info[4]);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
