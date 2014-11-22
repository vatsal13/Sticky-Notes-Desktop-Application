/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class StickyNotes extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        LoginController loginObj=new LoginController(stage);
        stage.setScene(new Scene(loginObj));
        stage.setTitle("Sticky Notes");
       // stage.setWidth(300);
       // stage.setHeight(200);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
     
}
