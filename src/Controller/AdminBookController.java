package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField nameTF;

    @FXML
    private TextField authorTF;

    @FXML
    private TextField typeTF;


    @FXML
    private TextField amountTF;

    @FXML
    private TextField priceTF;

    @FXML
    private Label labelMessage;


    @FXML
    void initialize() {
        assert nameTF != null : "fx:id=\"nameTF\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";
        assert authorTF != null : "fx:id=\"authorTF\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";
        assert typeTF != null : "fx:id=\"typeTF\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";
        assert amountTF != null : "fx:id=\"amountTF\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";
        assert labelMessage != null : "fx:id=\"labelMessage\" was not injected: check your FXML file 'AdminBookWindow.fxml'.";

    }
}

