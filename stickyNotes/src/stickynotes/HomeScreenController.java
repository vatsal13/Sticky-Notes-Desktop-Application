
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
public class HomeScreenController extends AnchorPane
{
        Stage stage;
        int uid;
        StickyMenuController stickyMenu;
        public HomeScreenController(Stage stage,int uid) 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homeScreen.fxml"));
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
        @FXML protected void onStickyClicked(MouseEvent event) throws Exception
        {
            if(stickyMenu==null)
                stickyMenu=new StickyMenuController(stage,uid);
            stage.setScene(new Scene(stickyMenu));
            stage.setTitle("Sticky Notes Menu");
            stage.show();
            


        }
        @FXML protected void onFlashCardsClicked(MouseEvent event) throws Exception
        {

                
            stage.setScene(new Scene(new FlashCardMenuController(stage,uid)));
            stage.setTitle("Flash Card Menu");
            stage.show();
            


        }
        @FXML protected void onPropertiesClicked(MouseEvent event) throws Exception
        {
                
            stage.setScene(new Scene(new PropertiesController(stage,uid)));
            stage.setTitle("Properties");
            stage.show();
            
            
        }
        @FXML protected void onLogoutClicked(MouseEvent event) throws Exception
        {
            System.out.println("loggin out");
            if(stickyMenu!=null)
            {
                System.out.println("handling notes");
                stickyMenu.handler.hideNotes();
                
                
            } 
               
            stage.setScene(new Scene(new LoginController(stage)));
            stage.setTitle("Sticky Notes");
            stage.show();
      
        }
        


}
