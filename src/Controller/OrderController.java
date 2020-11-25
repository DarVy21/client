package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OkButton;

    @FXML
    void initialize() {
        OkButton.setOnAction(actionEvent -> {
            OkButton.getScene().getWindow().hide();
        });
    }
}
