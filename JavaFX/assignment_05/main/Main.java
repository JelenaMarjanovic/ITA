package main;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL fxmlUrl = getClass().getClassLoader().getResource("view/main_screen.fxml");
        VBox root = FXMLLoader.<VBox>load(fxmlUrl);

        Scene scene = new Scene(root, 350, 200);

        scene.getStylesheets().add("view/style.css");

        if (Platform.isSupported(ConditionalFeature.UNIFIED_WINDOW)) {
            primaryStage.initStyle(StageStyle.UNIFIED);
        } else {
            primaryStage.initStyle(StageStyle.UTILITY);
        }

        primaryStage.setTitle("Prodaja internet paketa");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
