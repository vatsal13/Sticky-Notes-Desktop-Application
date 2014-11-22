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
public class LoadPackController extends AnchorPane
{
    Stage stage;
     int uid;
     flashCard flashCardObj;
     public ListView packList;
     @FXML Label errorMessage;
     public LoadPackController(Stage stage,int uid) 
     {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadPack.fxml"));
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
         //fetching packList
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
            
            stage.setScene(new Scene(new FlashCardMenuController(stage,uid)));
            stage.setTitle("Flash Card Menu");
          
          
          
      }       
      @FXML protected void onLoadClicked(ActionEvent event) throws Exception
      {
            //loading a pack for displaying
            //nameIndex of the pack which is to be loaded
            int nameIndex=packList.getSelectionModel().getSelectedIndex();
            if(nameIndex==-1)
            {
                //error message if no pack is selected
                errorMessage.setText("Select a Pack");
                
                
            }
            else
            {
                
                flashCardObj=new flashCard(uid);
                dbFlashCards dbObj=new dbFlashCards(uid);
                //fetching all cards of that pack and sending them for display
                flashCardObj.fetchAllCards(dbObj.packExists(getPackList().get(nameIndex)));
                //setting scene for displaying the pack ..calling view pack of flash card
                stage.setScene(new Scene(new FlashCardDisplayController(stage,uid,flashCardObj.viewPack(getPackList().get(nameIndex)))));
                stage.setTitle("Flash Cards");
          
                
            }
                
          
          
          
      }

}
