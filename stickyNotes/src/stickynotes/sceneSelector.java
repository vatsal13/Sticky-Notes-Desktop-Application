/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class sceneSelector {
    
         public void setScene(Stage stage, String fxmlfilename,AnchorPane controller) throws Exception{
               
                /*Parent root = FXMLLoader.load(getClass().getResource(fxmlfilename));
                //root.setUserData(ol);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                  stage.show();*/
               stage.setScene(new Scene(controller));
               stage.setTitle("Sticky Notes");
       // stage.setWidth(300);
       // stage.setHeight(200);
                stage.show();
    }
}










