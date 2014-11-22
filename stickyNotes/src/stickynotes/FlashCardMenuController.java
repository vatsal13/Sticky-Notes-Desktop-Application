/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class FlashCardMenuController extends AnchorPane
{
     Stage stage;
     int uid;
     public FlashCardMenuController(Stage stage,int uid) 
     {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flashMenu.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            this.uid=uid;
            this.stage=stage;
            try 
            {
                fxmlLoader.load();
                
            }    
            catch (IOException exception)
            {
                throw new RuntimeException(exception);
            }
            
     }
        @FXML protected void onCreateClicked(MouseEvent event) throws Exception
        {
            
                stage.setScene(new Scene(new CreatePackController(stage,uid)));
                stage.setTitle("Create Pack");
                stage.show();
                  



        }
        @FXML protected void onLoadClicked(MouseEvent event) throws Exception
        {
            
                  
                stage.setScene(new Scene(new LoadPackController(stage,uid)));
                stage.setTitle("Load Pack");
                stage.show();
                


        }
        @FXML protected void onDeleteClicked(MouseEvent event) throws Exception
        {
                stage.setScene(new Scene(new DeletePackController(stage,uid)));
                stage.setTitle("Delete Pack");
                stage.show();
                
                 
          


        }
        @FXML protected void onLogoutClicked(MouseEvent event) throws Exception
        {
            
            stage.setScene(new Scene(new LoginController(stage)));
            stage.setTitle("Sticky Notes");
            stage.show();
            
            


        }
        @FXML protected void onBackClicked(MouseEvent event) throws Exception
        {
            stage.setScene(new Scene(new HomeScreenController(stage,uid)));
            stage.setTitle("Home Screen");
            stage.show();


        }
        








}
