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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void initialize()  {

        try {
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            BasketEntity basket= new BasketEntity();
            ObservableList<BasketEntity> baskets = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                String[] infoString = list.get(i).split(" ", 3);
                basket.setAmount(Integer.parseInt(infoString[1]));
                basket.setName(infoString[0]);
                basket.setPrice(Double.parseDouble(infoString[2]));
                baskets.add(basket);
            }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table.setItems(baskets);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

