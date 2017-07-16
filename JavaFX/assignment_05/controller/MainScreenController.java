package controller;

import java.io.IOException;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainScreenController {

    @FXML
    private void openAddWindow() throws IOException {

        GridPane rootAdd = FXMLLoader.<GridPane>load(getClass().getClassLoader().getResource("view/add_contract.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(rootAdd);

        scene.getStylesheets().add("view/style.css");

        if (Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW)) {
            stage.initStyle(StageStyle.UNIFIED);
        } else {
            stage.initStyle(StageStyle.UTILITY);
        }

        stage.setTitle("Dodavanje ugovora");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void openShowWindow() throws IOException {

        VBox rootShow = FXMLLoader.<VBox>load(getClass().getClassLoader().getResource("view/show_contracts.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(rootShow);

        scene.getStylesheets().add("view/style.css");

        if (Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW)) {
            stage.initStyle(StageStyle.UNIFIED);
        } else {
            stage.initStyle(StageStyle.UTILITY);
        }

        stage.setTitle("Prikaz ugovora");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void openDeleteWindow() throws IOException {

        VBox rootDelete = FXMLLoader.<VBox>load(getClass().getClassLoader().getResource("view/delete_contract.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(rootDelete);

        scene.getStylesheets().add("view/style.css");

        if (Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW)) {
            stage.initStyle(StageStyle.UNIFIED);
        } else {
            stage.initStyle(StageStyle.UTILITY);
        }

        stage.setTitle("Brisanje ugovora");
        stage.setScene(scene);
        stage.show();

    }

}
