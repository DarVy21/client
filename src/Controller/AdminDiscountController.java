package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import Entity.DiscountEntity;
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

public class AdminDiscountController {

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
    private TableColumn<DiscountEntity, Integer> clientId;

    @FXML
    private Button backButton;

    @FXML
    private TextField deleteIdTF;

    @FXML
    private Button deletePromocodButton;

    @FXML
    private Button AddDiscountButton;
    @FXML
    private Label labelMessage;

    @FXML
    void initialize() {
        showDiscount();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminMainWindow.fxml");
        });
        deletePromocodButton.setOnAction(actionEvent -> {
            String deleteId= deleteIdTF.getText();
            String message="Discount,deleteDiscount,"+deleteId;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
                if(message.equals("success")) showDiscount();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        AddDiscountButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AddDiscountWindow.fxml");

        });

    }

    private void openSecondWin(String win) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(win));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            return;
        }
        Stage inputStage = new Stage();
        inputStage.initOwner(backButton.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.show();
    }

    public void showDiscount() {
        String message="Discount,showDiscountAdmin";
        try {
            Client.os.writeObject(message);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<DiscountEntity> discounts = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                DiscountEntity discount = new DiscountEntity();
                String[] infoString = list.get(i).split(",", 4);
                discount.setDiscountSize(Integer.parseInt(infoString[2]));
                discount.setPromocod(infoString[1]);
                discount.setId_discount(Integer.parseInt(infoString[0]));
                discount.setUser_id(Integer.parseInt(infoString[3]));
                discounts.add(discount);
            }
            promocod.setCellValueFactory(new PropertyValueFactory<>("promocod"));
            discountSize.setCellValueFactory(new PropertyValueFactory<>("discountSize"));
            id.setCellValueFactory(new PropertyValueFactory<>("id_discount"));
            clientId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            table.setItems(discounts);
        } catch (IOException | ClassNotFoundException e) {
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