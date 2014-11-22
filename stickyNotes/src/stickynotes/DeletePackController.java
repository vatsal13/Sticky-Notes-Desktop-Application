/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class DeletePackController extends AnchorPane 
{
     Stage stage;
     int uid;
     flashCard flashCardObj;
     public ListView packList;
     @FXML Label errorMessage;
     public DeletePackController(Stage stage,int uid) 
     {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deletePack.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            this.uid=uid;
            this.stage=stage;
            try 
            {
                fxmlLoader.load();
                packList.setItems(FXCollections.observableArrayList(getPackList()));
                
            }    
            catch (IOException exception)
            {
                throw new RuntimeException(exception);
            }
            
     }
     private ArrayList<String> getPackList()
     {
         //fetching packList to be displayed
         flashCard obj=new flashCard(uid);
         String []s=obj.fetchPackList();
         ArrayList<String> packList=new ArrayList<String>();
         for(int i=0;i<s.length;i++)
         {
             if(s[i]==null)
                 break;
             packList.add(s[i]);
             
             
         }
         return packList;
         
     }
    
     
     @FXML protected void onBackClicked(MouseEvent event) throws Exception
      {
            //displaying flash card menu again
            stage.setScene(new Scene(new FlashCardMenuController(stage,uid)));
            stage.setTitle("Flash Card Menu");
          
          
          
      }        
     @FXML protected void onDeleteClicked(ActionEvent event) throws Exception
      {
            //
            int nameIndex=packList.getSelectionModel().getSelectedIndex();
            if(nameIndex==-1)
            {
                errorMessage.setText("Select a Pack");
                
                
            }
            else
            {
                flashCardObj=new flashCard(uid);
                //checking if pack is being displayed
                if(flashCardObj.isSelectedPackBeingDisplayed())
                {
                    flashCardObj.hidePack();
                    
                }
                //displaying that the pack was successfully deleted;
                errorMessage.setText(getPackList().get(nameIndex)+" deleted");
                //deleting that pack from database
                flashCardObj.deletePack(getPackList().get(nameIndex));
                //reloading the list of packs
                packList.setItems(FXCollections.observableArrayList(getPackList()));
                
                
            }
                
          
          
          
      }




}
