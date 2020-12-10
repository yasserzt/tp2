/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author i__t__s
 */
public class BookStore extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Home.fxml"));
     //   System.out.println(getClass().getResource("").toString());
        primaryStage.setTitle("Bookstore EWA");
     //   primaryStage.getIcons().add(new Image("/bookstore/icons/icon.png"));
        primaryStage.setScene(new Scene(root));
     //  primaryStage.getScene().getStylesheets().add("bookstore/css/homeStyling.css");
        primaryStage.show();
               
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
