package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {

        mainScene = new Scene(loadFXML("login"), 640, 400);
        stage.setScene(mainScene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        mainScene.setRoot(loadFXML(fxml));


    }

    static void changeView(String view,double width,double height){

        try {
            mainScene.getWindow().setWidth(width);
            mainScene.getWindow().setHeight(height);
            setRoot(view);

        }catch (Exception e){
            e.printStackTrace();

        }


    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}