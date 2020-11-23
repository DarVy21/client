package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Entity.BookEntity;
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
    private TableView<BookEntity> Book;

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
    void initialize() {
        showBook();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });
        AddToBasket.setOnAction(event -> {
            String IdBookText = TextFieldBookChoice.getText().trim();
            if (IdBookText.matches("\\d+")==false){
                LabelMessage.setText("Ошибка, повторите ввод.");
            }
            else{
                String message="Basket,addToBasket,"+IdBookText+","+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    message= (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(message.equals("success"))
                    LabelMessage.setText("Книга добавлена в корзину.");
                else LabelMessage.setText("Ошибка.");

            }
        });
    }
    public void showBook(){
        try {
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<BookEntity> books = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                BookEntity book=new BookEntity();
                String[] infoString = list.get(i).split(" ", 6);
                book.setIdBook(Integer.parseInt(infoString[0]));
                book.setName(infoString[1]);
                book.setAuthor(infoString[2]);
                book.setType(infoString[3]);
                book.setPrice(Double.parseDouble(infoString[4]));
                book.setAmount(Integer.parseInt(infoString[5]));

                books.add(book);
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id_book"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            Book.setItems(books);
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
