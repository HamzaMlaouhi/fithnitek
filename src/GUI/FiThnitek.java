/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asus
 */
public class FiThnitek extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
       // stage.initStyle(StageStyle.UNDECORATED);
=======
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
>>>>>>> d8e4ff506f784431a3e9ad29ef6023eea8e1ec49
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
