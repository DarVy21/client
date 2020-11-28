package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.BasketEntity;
import Entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableOfOrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderEntity> Table;

    @FXML
    private TableColumn<OrderEntity, Integer> OrderNumber;

    @FXML
    private TableColumn<OrderEntity, Integer> Amount;

    @FXML
    private TableColumn<OrderEntity, Double> Price;

    @FXML
    private TableColumn<OrderEntity, String> Status;

    @FXML
    private Button BackButton;



    @FXML
    void initialize() {
        showOrder();
        BackButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });
    }


    private void showOrder() {
        String message="Order,showOrder,"+Client.getId_user();
        try {
            Client.os.writeObject(message);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<OrderEntity> orders = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                OrderEntity order = new OrderEntity();
                String[] infoString = list.get(i).split(",", 4);
                order.setOrderNumber(Integer.parseInt(infoString[0]));
                order.setTotalAmount(Integer.parseInt(infoString[1]));
                order.setTotalPrice(Double.parseDouble(infoString[2]));
                order.setStatus(infoString[3]);
                orders.add(order);
            }
            OrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
            Amount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
            Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
            Status.setCellValueFactory(new PropertyValueFactory<>("status"));
            Table.setItems(orders);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void openNewScene(String window) {
        BackButton.getScene().getWindow().hide();

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