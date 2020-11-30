package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button userButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button BookButton;
    @FXML
    private Button backButton;
    @FXML
    private Button PromocodButton;


    @FXML
    void initialize() {
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/LogInWindow.fxml");
        });
        userButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminUserWindow.fxml");
        });
        BookButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminBookWindow.fxml");
        });
        OrderButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminOrdersTableWindow.fxml");
        });
        PromocodButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminDiscountWindow.fxml");
        });
    }

    public void openNewScene(String window)
    {
        BookButton.getScene().getWindow().hide();

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