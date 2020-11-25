package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entity.BasketEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (message.equals("success"))
                openOrderWindow();
        });

    }

    public void showBasket() {
        try {
            String clientMessage = "Basket,ShowBasket,"+Client.getId_user();
            String msg= clientMessage;
            Client.os.writeObject(clientMessage);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<BasketEntity> baskets = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                BasketEntity basket = new BasketEntity();
                String[] infoString = list.get(i).split(",", 4);
                basket.setAmount(Integer.parseInt(infoString[1]));
                basket.setName(infoString[0]);
                basket.setPrice(Double.parseDouble(infoString[2]));
                basket.setId(Integer.parseInt(infoString[3]));
                baskets.add(basket);
            }
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
    public void openOrderWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Window/OrderWindow.fxml"));
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