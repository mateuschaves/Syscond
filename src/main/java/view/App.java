package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene mainScene;


    @Override
    public void start(Stage stage) throws IOException {

        mainScene = new Scene(loadFXML("login"), 600, 400);
        stage.setScene(mainScene);
        stage.getIcons().add(new Image("/img/syscondLogo.png"));
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}