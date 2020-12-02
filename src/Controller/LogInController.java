package Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button AuthSignUpButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    private CheckBox AdminLogInWindow;

    @FXML
    private CheckBox UserLogInWindow;

    @FXML
    private Label CautionLabel;

    public String message;

    @FXML
    void initialize()
    {

        Client.Connect();

        AuthSignUpButton.setOnAction(event -> {
            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();
            if (UserLogInWindow.isSelected() && !AdminLogInWindow.isSelected() && login!=null&& password!=null) {
                String clientMessage = "User,"+"checkSingInClient," + login + "," + password;
                try {
                    Client.os.writeObject(clientMessage);
                    message = (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (message.equals("fail"))
                    CautionLabel.setText("Такого пользователя не существует!");
                else {
                    Client.setId_user(Integer.parseInt(message));
                    openNewScene("/Window/ClientMainWindow.fxml");
                }
            } else if (AdminLogInWindow.isSelected() && !UserLogInWindow.isSelected()) {
                String clientMessage = "User,checkSingInAdmin," + login + "," + password;

                try {
                    Client.os.writeObject(clientMessage);
                    message = (String) Client.is.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (message.equals("successAdmin")) {
                    openNewScene("/Window/AdminMainWindow.fxml");
                } else if (message.equals("fail"))
                    CautionLabel.setText("Такого администратора не существует!");
            } else
                CautionLabel.setText("Пожалуйста, выберите администратор либо пользователь!");


        });

        LoginSignUpButton.setOnAction(event ->
        {
            if(UserLogInWindow.isSelected() && !AdminLogInWindow.isSelected()) {
                openNewScene("/Window/RegisterWindow.fxml");
            } else if (AdminLogInWindow.isSelected() && !UserLogInWindow.isSelected())
                CautionLabel.setText("Вы можете зарегистрироваться только как пользователь!");
            else
                CautionLabel.setText("Пожалуйста, выберите администратор или пользователь!");
        });
    }

    public void openNewScene(String window)
    {
        LoginSignUpButton.getScene().getWindow().hide();

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


