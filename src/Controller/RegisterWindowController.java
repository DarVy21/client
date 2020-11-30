package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField SignUpSurname;

    @FXML
    private CheckBox SignUpCheckBoxMale;

    @FXML
    private CheckBox SignUpCheckBoxFemale;

    @FXML
    private TextField SignUpCountry;

    @FXML
    private CheckBox SignUpCheckBoxOther;

    @FXML
    void initialize()
    {
        SignUpButton.setOnAction(event ->
        {

            String login = LoginField.getText();
            String Password = PasswordField.getText();

            String clM = "User,checkLogin," + login;
            try {
                Client.os.writeObject(clM);
                String mes = (String)Client.is.readObject();
                if(mes.equals("success"))
                {
                    String clientMessage = "User,addClient,"+ login + "," + Password;
                    try {
                        Client.os.writeObject(clientMessage);
                        mes= (String) Client.is.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Client.setId_user(Integer.parseInt(mes));
                    openNewScene("/Window/ClientMainWindow.fxml");
                }
                else
                {
                    LoginField.clear();
                    LoginField.setPromptText("Пользователь с таким логином уже существует");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

    }

    public void openNewScene(String window)
    {
        SignUpButton.getScene().getWindow().hide();

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