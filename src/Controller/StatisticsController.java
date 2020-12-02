package Controller;

import Server.Entities.BookEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController {
    @FXML
    private Button backButton;

    @FXML
    private PieChart pieChart;

    @FXML
    void initialize() {
        showStatistics();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminMainWindow.fxml");
        });
    }

    public void showStatistics() {
        try {
            String clientMessage = "Book,showTypes";
            Client.os.writeObject(clientMessage);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList(
                    new PieChart.Data("Роман", Double.valueOf(list.get(0))),
                    new PieChart.Data("Детектив", Double.valueOf(list.get(1))),
                    new PieChart.Data("Научные", Double.valueOf(list.get(2))),
            new PieChart.Data("Антиутопия", Double.valueOf(list.get(3))),
                    new PieChart.Data("Психология", Double.valueOf(list.get(4))),
                    new PieChart.Data("Утопия", Double.valueOf(list.get(5))),
                    new PieChart.Data("Другое", Double.valueOf(list.get(6)))
            );
            pieChart.setData(pieChartList);

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
