package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Server.Entities.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ClientMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Basket;

    @FXML
    private Button Book;

    @FXML
    private Button HistoryOrder;

    @FXML
    private Button backButton;
    @FXML
    private Button PromocodButton;
    @FXML
    private ListView<String> notificationView;


    @FXML
    void initialize() {

        showNotifications();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/LogInWindow.fxml");
        });

        Book.setOnAction(actionEvent -> {

            openNewScene("/Window/ShowBookWindow.fxml");
        });
        Basket.setOnAction(actionEvent -> {

            openNewScene("/Window/ShowBasketWindow.fxml");
        });
        HistoryOrder.setOnAction(actionEvent -> {
            openNewScene("/Window/TableOfOrdersWindow.fxml");
        });
        PromocodButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientDiscountWindow.fxml");
        });


    }

    public void showNotifications(){
        String message="Order,showNotifications,"+Client.getId_user();
        try {
            Client.os.writeObject(message);
            List<String> list = (List<String>) Client.is.readObject();
            if (list.size()!=0) {
                ObservableList<String> notlist = FXCollections.observableArrayList(list);
                ListView<String> notifications = new ListView<>(notlist);
                notificationView.setItems(notlist);
            }
            else  notificationView.setVisible(false);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void openNewScene(String window)
    {
        Book.getScene().getWindow().hide();

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