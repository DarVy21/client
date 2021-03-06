package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import Server.Entities.BookEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookEntity> table;

    @FXML
    private TableColumn<BookEntity, Integer> id;

    @FXML
    private TableColumn<BookEntity, String> name;

    @FXML
    private TableColumn<BookEntity, String> author;

    @FXML
    private TableColumn<BookEntity, String> type;

    @FXML
    private TableColumn<BookEntity, Double> price;

    @FXML
    private TableColumn<BookEntity, Integer> amount;

    @FXML
    private TextField TextFieldBookChoice;

    @FXML
    private Button AddToBasket;

    @FXML
    private Label LabelMessage;
    @FXML
    private Button backButton;

    @FXML
    private TextField AmountTF;


    @FXML
    void initialize() {
        showBook();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });
        AddToBasket.setOnAction(event -> {
            String IdBookText = TextFieldBookChoice.getText().trim();
            String amountBookText=AmountTF.getText().trim();
            if (IdBookText.matches("\\d+")==false || amountBookText.matches("\\d+")==false || Integer.parseInt(amountBookText)<0)
                LabelMessage.setText("Ошибка, повторите ввод.");
            else{
                String message="Basket,addToBasket,"+IdBookText+","+amountBookText+","+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    message= (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(message.equals("success")){
                    LabelMessage.setText("Книга добавлена в корзину.");
                    showBook();}
                else LabelMessage.setText("Ошибка.");

            }
        });
    }
    public void showBook(){
        try {
            String clientMessage = "Book,ShowBook";
            Client.os.writeObject(clientMessage);
            List<BookEntity> list= (List<BookEntity>) Client.is.readObject();
            ObservableList<BookEntity> books = FXCollections.observableArrayList();
            for (BookEntity book:list)
                books.add(book);
            id.setCellValueFactory(new PropertyValueFactory<>("id_book"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            table.setItems(books);
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
