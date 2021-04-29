package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Cylinder;
import javafx.stage.*;
import javafx.scene.control.PopupControl;

import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MenuController {

    @FXML
    private AnchorPane anchorPane;

    public void exit(){
        System.exit(0);
    }



    public void showPopup(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/teste.fxml"));
        }catch (Exception e){

        }
        Node node = (Node) root;
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);

        System.out.println("-> " + anchorPane.getChildren());

    }
}
