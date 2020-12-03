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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminOrdersTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrdersEntity> Table;

    @FXML
    private TableColumn<OrdersEntity, Integer> OrderNumber;

    @FXML
    private TableColumn<OrdersEntity, Integer> Amount;

    @FXML
    private TableColumn<OrdersEntity, Double> Price;

    @FXML
    private TableColumn<OrdersEntity, String> Status;

    @FXML
    private Button BackButton;
    @FXML
    private TextField idOrder;
    @FXML
    private ComboBox<String> orderStatus;
    @FXML
    private Button changeButton;
    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        showOrder();
        BackButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminMainWindow.fxml");
        });
        changeButton.setOnAction(actionEvent -> {
            changeStatus();
            showOrder();
        });
    }

    private void changeStatus(){
        String idOrderTF=idOrder.getText().trim();
        String statusOrderCB = orderStatus.getValue().trim();
        String message="Order,changeStatus,"+idOrderTF+","+statusOrderCB;
        try {
            Client.os.writeObject(message);
            message= (String) Client.is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(message.equals("success")){
            MessageLabel.setText("Статус изменен.");

        }
        else MessageLabel.setText("Ошибка.");


    }

    private void showOrder() {
        String message="Order,showOrderAdmin";
        try {
            Client.os.writeObject(message);
            List<OrdersEntity> list = (List<OrdersEntity>) Client.is.readObject();
            ObservableList<OrdersEntity> orders = FXCollections.observableArrayList();
            for (OrdersEntity order: list)
                orders.add(order);
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