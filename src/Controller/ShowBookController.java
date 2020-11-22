package Controller;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entity.BasketEntity;
import Entity.BookEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void initialize() {
        try {
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<BookEntity> books = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                BookEntity book=new BookEntity();
                String[] infoString = list.get(i).split(" ", 6);
                book.setId_book(Integer.parseInt(infoString[0]));
                book.setName(infoString[1]);
                book.setAuthor(infoString[2]);
                book.setType(infoString[3]);
                book.setAmount(Integer.parseInt(infoString[4]));
                book.setPrice(Double.parseDouble(infoString[5]));
                books.add(book);
            }
        id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
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
                if(message=="success")
                LabelMessage.setText("Книга добавлена в корзину.");
                else LabelMessage.setText("Ошибка.");
            }
        });
    }
}

