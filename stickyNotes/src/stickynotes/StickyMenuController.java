/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.io.IOException;
import javafx.event.ActionEvent;
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
public class StickyMenuController extends AnchorPane 
{
        Stage stage;
        int uid;
        noteHandler handler;
        public StickyMenuController(Stage stage,int uid) 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stickyMenu.fxml"));
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
            System.out.println("Making handler wid uid "+uid);
            handler=new noteHandler(stage,uid);
    
        }
        @FXML protected void onLoadClicked(MouseEvent event) throws Exception
        {
            
                  
                  handler.displayNotes();



        }
        @FXML protected void onHideClicked(MouseEvent event) throws Exception
        {
                
                 handler.hideNotes();



        }
        @FXML protected void onLogoutClicked(MouseEvent event) throws Exception
        {
            handler.hideNotes();
            stage.setScene(new Scene(new LoginController(stage)));
            stage.setTitle("Sticky Notes");
            stage.show();
            
            


        }
        @FXML protected void onBackClicked(MouseEvent event) throws Exception
        {
            handler.hideNotes();
            stage.setScene(new Scene(new HomeScreenController(stage,uid)));
            stage.setTitle("Home Screen");
            stage.show();


        }
        


        




}
