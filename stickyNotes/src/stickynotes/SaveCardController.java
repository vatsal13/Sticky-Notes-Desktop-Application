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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class SaveCardController extends AnchorPane
{
    Stage stage;
    int uid;
    card cardToBeEdited;
    @FXML private Label errorMessage;
    flashCardPack pack;
    @FXML private TextArea frontContent;
    @FXML private TextArea backContent;
    public SaveCardController(Stage stage,int uid,flashCardPack pack,card cardToBeEdited) 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.stage=stage;
        this.uid=uid;
        this.pack=pack;
        this.cardToBeEdited=cardToBeEdited;
        try 
        {
            fxmlLoader.load();
            frontContent.setText(cardToBeEdited.getFront());
            backContent.setText(cardToBeEdited.getBack());
        } 
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    
    }
    @FXML protected void onSaveClicked(ActionEvent event) throws Exception
    {
            if(frontContent.getText().length()!=0&&backContent.getText().length()!=0)
            {
                cardToBeEdited.setFront(frontContent.getText());
                cardToBeEdited.setBack(backContent.getText());
                pack.saveCard(cardToBeEdited);
                stage.setScene(new Scene(new FlashCardDisplayController(stage,uid,pack)));
                stage.setTitle("Flash Cards");
            }
            errorMessage.setText("Either front or back must have some value");
        
    }






}
