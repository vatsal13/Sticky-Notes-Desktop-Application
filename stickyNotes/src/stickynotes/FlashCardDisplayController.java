package stickynotes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dilipjain
 */
public class FlashCardDisplayController extends AnchorPane
{
    Stage stage;
    int uid;
    card cardBeingDisplayed;
    flashCardPack pack;
    ArrayList<card> cardsList;
    Iterator<card> cardsIterate;
    @FXML private Label front;
    @FXML private Label back;
    public FlashCardDisplayController(Stage stage,int uid,flashCardPack pack) 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flashCardsDisplay.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.stage=stage;
        this.uid=uid;
        this.pack=pack;
        try 
        {
            fxmlLoader.load();
            flashCardProperties properties=new flashCardProperties(uid,"cards");
            String style;
                //setting font properties of flash cards
                Font font=properties.fetchFontProperties();
                style="-fx-text-fill:"+font.fontColor+";";
                style+="-fx-font-family:"+font.fontFamily+";";
                style+="-fx-font-size:"+font.fontSize+";";
                front.setStyle(style);
                back.setStyle(style);
            front.setVisible(false);
            back.setVisible(false);
            //fetching all card belonging to that card
            cardsList=pack.displayPack();
            if(cardsList.size()==0)
            {
                //if there are no cards in that pack add a dummy card
                pack.createCard("Your Dummy Card..Edit it","Place Your Answer ");
                cardsList=pack.displayPack();
            }
            cardsIterate= cardsList.iterator();
            iterate();
        } 
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    
    }
    public void iterate()
    {
        //displaying next card on click of next icon
        if(cardsList.size()!=1)
        {
            //if no.of cards >1 then using circular array approach to display cards
            if(cardsIterate.hasNext())
            {
                cardBeingDisplayed=cardsIterate.next();
                front.setText(cardBeingDisplayed.getFront());
                back.setText(cardBeingDisplayed.getBack());
                front.setVisible(true);
                back.setVisible(false);
            }
            else
            {
                cardsIterate=cardsList.iterator();
                iterate();
            
            }
        }
        else
        {
            //if no.of cards is just 1 simply displays that
            cardBeingDisplayed=cardsList.get(0);
            front.setText(cardBeingDisplayed.getFront());
            back.setText(cardBeingDisplayed.getBack());
            front.setVisible(true);
            back.setVisible(false);    
            
            
        }
        
        
    }
    @FXML protected void onNewClicked(MouseEvent event) throws Exception
    {
        //loading scene for creating new card
        stage.setScene(new Scene(new CreateCardController(stage,uid,pack)));
        stage.setTitle("Create Card");
        
    }
    @FXML protected void onEditClicked(MouseEvent event) throws Exception
    {
        //loading scene for editing the card being displayed
        stage.setScene(new Scene(new SaveCardController(stage,uid,pack,cardBeingDisplayed)));
        stage.setTitle("Edit Card");
        

    }
    @FXML protected void onDeleteClicked(MouseEvent event) throws Exception
    {
        //deleting card from the pack
        pack.deleteCard(cardBeingDisplayed.getCardId());
        flashCard flashCardObj=new flashCard(uid);
        flashCardObj.savePack();
        //loading the fresh list of cards and displaying them
        cardsList=pack.displayPack();
        if(cardsList.size()==0)
        {
            //if the card being deleted was the last card in pack add a dummy card
            pack.createCard("Your dummy card..Edit it","Place Your Answer ");
            cardsList=pack.displayPack();
        }
        //redisplaying the list of cards
        cardsIterate=cardsList.iterator();
        iterate();
            

    }
    @FXML protected void onNextClicked(MouseEvent event) throws Exception
    {
            //displaying next card in the pack
            iterate();

    }
    @FXML protected void onBackButtonClicked(ActionEvent event) throws Exception
    {
            //displaying the flash card menu
            stage.setScene(new Scene(new FlashCardMenuController(stage,uid)));
            stage.setTitle("Flash Card Menu");

    }
    @FXML protected void onLogoutClicked(MouseEvent event) throws Exception
    {
            //logout of the system
            stage.setScene(new Scene(new LoginController(stage)));
            stage.setTitle("Sticky Notes");
            stage.show();
    

    }
    @FXML protected void onFrontClicked(MouseEvent event) throws Exception
    {
            //display front content of the card
            front.setVisible(false);
            back.setVisible(true);
    

    }
    @FXML protected void onBackClicked(MouseEvent event) throws Exception
    {
            //display back content of the card
            front.setVisible(true);
            back.setVisible(false);
    

    }

}
