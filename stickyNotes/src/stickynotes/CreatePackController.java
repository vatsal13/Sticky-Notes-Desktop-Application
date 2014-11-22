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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class CreatePackController extends AnchorPane 
{
     Stage stage;
     int uid;
     @FXML private TextField packName;
     @FXML private Label errorMessage;
     public CreatePackController(Stage stage,int uid) 
     {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createPack.fxml"));
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
     @FXML protected void onBackClicked(MouseEvent event) throws Exception
     {
         //displaying flash card menu 
          stage.setScene(new Scene(new FlashCardMenuController(stage,uid)));
          stage.setTitle("Flash Card Menu");
          stage.show();
         
         
         
     
     }        
     @FXML protected void onCreateClicked(ActionEvent event) throws Exception
     {
         //creating flashcard obj and call its create pack method
          flashCard flashCardObj=new flashCard(uid);
          if(flashCardObj.createPack(packName.getText())==-1)
          {
                //if pack with such name already exist display error message
                 errorMessage.setText("Pack already exists,Try different name*");
                 
                  
          
          }
          else
              errorMessage.setText("Pack Successfully Created");
         
         
     
     }     





}
