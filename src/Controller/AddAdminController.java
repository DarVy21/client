package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private Button addButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            String login = loginTF.getText().trim();
            String password = passwordTF.getText().trim();
            if(login!=null && password!=null) {
                String message = "User,checkLogin," + login;
                try {
                    Client.os.writeObject(message);
                    message = (String) Client.is.readObject();
                    if (message.equals("success")) {
                        String clientMessage = "User,addAdmin," + login + "," + password;
                        try {
                            Client.os.writeObject(clientMessage);
                            message = (String) Client.is.readObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (message.equals("success")) MessageLabel.setText("Админ добавлен.");
                    } else MessageLabel.setText("Пользователь с таким логином уже существует");
                } catch (IOException |ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}