package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.Entities.BasketEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowBasketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BasketEntity> table;

    @FXML
    private TableColumn<BasketEntity, String> name;

    @FXML
    private TableColumn<BasketEntity, Integer> amount;

    @FXML
    private TableColumn<BasketEntity, Double> price;
    @FXML
    private TableColumn<BasketEntity, Integer> id;
    @FXML
    private TextField deleteIdTF;

    @FXML
    private Button deleteBasketButton;
    @FXML
    private Button backButton;
    @FXML
    private Button OrderButton;

    @FXML
    private Label labelMessage;

    @FXML
    void initialize() {
        showBasket();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });
        deleteBasketButton.setOnAction(actionEvent -> {
            String idBasket=deleteIdTF.getText().trim();
            String message="Basket,deleteBasket,"+idBasket;
            try {
                Client.os.writeObject(message);
                message=(String) Client.is.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (message.equals("success"))
                showBasket();
        });
        OrderButton.setOnAction(actionEvent -> {
            String  message="Order,addToOrder,"+Client.getId_user();
            String clientMessage = "Basket,ShowBasket,"+Client.getId_user();
            try {

                Client.os.writeObject(clientMessage);
                ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
                if (list.size() == 0){
                    labelMessage.setText("Ваша корзина пуста!");
                }
                else {
                    Client.os.writeObject(message);
                    message = (String) Client.is.readObject();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (message.equals("success"))
                openOrderDiscountWindow();
        });

    }

    public void showBasket() {
        try {
            String clientMessage = "Basket,ShowBasket,"+Client.getId_user();
            Client.os.writeObject(clientMessage);
            List<BasketEntity> list=(List<BasketEntity>)Client.is.readObject();
            ObservableList<BasketEntity> baskets = FXCollections.observableArrayList();
            for (BasketEntity basket :list)
                baskets.add(basket);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.setItems(baskets);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    public void openOrderDiscountWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Window/OrderDiscountWindow.fxml"));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            return;
        }
        Stage inputStage = new Stage();
        inputStage.initOwner(OrderButton.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.show();
    }
}