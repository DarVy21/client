package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import Entity.DiscountEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientDiscountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DiscountEntity> table;

    @FXML
    private TableColumn<DiscountEntity, String> promocod;

    @FXML
    private TableColumn<DiscountEntity, Integer> discountSize;

    @FXML
    private TableColumn<DiscountEntity, Integer> id;

    @FXML
    private Button backButton;


    @FXML
    private Label labelMessage;

    @FXML
    void initialize() {
        showDiscount();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });

    }

    public void showDiscount() {
        try {
            String clientMessage = "Discount,ShowDiscount,"+Client.getId_user();
            Client.os.writeObject(clientMessage);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<DiscountEntity> discounts = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                DiscountEntity discount = new DiscountEntity();
                String[] infoString = list.get(i).split(",", 3);
                discount.setDiscountSize(Integer.parseInt(infoString[2]));
                discount.setPromocod(infoString[1]);
                discount.setId_discount(Integer.parseInt(infoString[0]));
                discounts.add(discount);
            }
            promocod.setCellValueFactory(new PropertyValueFactory<>("promocod"));
            discountSize.setCellValueFactory(new PropertyValueFactory<>("discountSize"));
            id.setCellValueFactory(new PropertyValueFactory<>("id_discount"));
            table.setItems(discounts);
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

}