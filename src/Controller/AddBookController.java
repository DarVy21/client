package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private TextField author;

    @FXML
    private TextField amount;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button addButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {

    }
}