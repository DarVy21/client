package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Server.Entities.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> editChoice;

    @FXML
    private TextField editTF;

    @FXML
    private Button editButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        BookEntity book = (BookEntity) Client.is.readObject();
        BookEntity finalBook = book;
        editButton.setOnAction(actionEvent -> {
            String edit=editTF.getText().trim();
            if(edit!=null){
                try {
                    switch (editChoice.getValue()){
                        case "Название": finalBook.setName(edit);
                            Client.os.writeObject(finalBook);
                            break;
                        case "Автор": finalBook.setAuthor(edit);
                            Client.os.writeObject(finalBook);
                            break;
                        case "Жанр": finalBook.setType(
                                edit);
                            Client.os.writeObject(finalBook);
                            break;
                        case "Цена":finalBook.setPrice(Double.parseDouble(edit));
                            Client.os.writeObject(finalBook);
                            break;
                        case "Количество":finalBook.setAmount(Integer.parseInt(edit));
                            Client.os.writeObject(finalBook);
                    }
                    String message= (String) Client.is.readObject();
                    if (message.equals("success")) MessageLabel.setText("Книга отредактирована");
                    else MessageLabel.setText("Ошибка.");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}