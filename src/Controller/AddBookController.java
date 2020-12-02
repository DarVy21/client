package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField price;



    @FXML
    private TextField author;

    @FXML
    private TextField amount;
    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            addBook();
        });

        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
        });
    }

    private void addBook() {
        String authorTF=author.getText().trim();
        String nameTF =name.getText().trim();
        String typeTF=type.getValue().trim();
        String amountTF = amount.getText().trim();
        String priceTF = price.getText().trim();
        if (Integer.parseInt(amountTF)<=0|| Double.parseDouble(priceTF)<=0)
            MessageLabel.setText("Ошибка, повторите ввод.");
        else{
            String message="Book,addBook,"+nameTF+","+authorTF+","+typeTF+","+amountTF+","+priceTF;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(message.equals("success")){
                MessageLabel.setText("Книга добавлена.");
             }
            else MessageLabel.setText("Ошибка.");

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