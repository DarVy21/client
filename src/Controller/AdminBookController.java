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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookEntity> table;

    @FXML
    private TableColumn<BookEntity, Integer> id;

    @FXML
    private TableColumn<BookEntity, String> type;

    @FXML
    private TableColumn<BookEntity, String> name;

    @FXML
    private TableColumn<BookEntity, String> author;

    @FXML
    private TableColumn<BookEntity, Integer> amount;

    @FXML
    private TableColumn<BookEntity, Double> price;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idTF;

    @FXML
    void initialize() {
        showBook();
        deleteButton.setOnAction(actionEvent -> {
            String deleteId= idTF.getText();
            String message="Book,deleteBook,"+deleteId;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
                if(message.equals("success")) showBook();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(actionEvent ->{
            openNewScene("/Window/AdminMainWindow.fxml");
        } );
        addButton.setOnAction(actionEvent -> {
            openSecondWin("/Window/AddBookWindow");
           // showBook();
        });
        editButton.setOnAction(actionEvent -> {
            String editId= idTF.getText();
            String message="Book,editBook,"+editId;
            try {
                Client.os.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            openSecondWin("/Window/EditBookWindow.fxml");
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

    public void showBook(){
        try {
            String clientMessage = "Book,ShowBook";
            Client.os.writeObject(clientMessage);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<BookEntity> books = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                BookEntity book=new BookEntity();
                String[] infoString = list.get(i).split(",", 6);
                book.setId_book(Integer.parseInt(infoString[0]));
                book.setName(infoString[1]);
                book.setAuthor(infoString[2]);
                book.setType(infoString[3]);
                book.setAmount(Integer.parseInt(infoString[4]));
                book.setPrice(Double.parseDouble(infoString[5]));
                books.add(book);
            }
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
    public void openNewScene(String window){
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