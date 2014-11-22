/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.util.ArrayList;

/**
 *
 * @author dilipjain
 */
public class flashCardPack
{
            int uid;
            int packId;
            dbFlashCards dbObj;
            flashCardPack(int uid,int packId)
            {
                this.uid=uid;
                this.packId=packId;
                
            }
            public ArrayList<card> displayPack()
            {
                //returns a list of all cards present in that pack ,fetches from database
                dbObj=new dbFlashCards(uid);
                return dbObj.fetchAllCards(packId);
                
                
                
            }
            public void createCard(String front,String back)
            {
                //creates a new card and adds it to the pack and adds it to database
                dbObj=new dbFlashCards(uid);
                dbObj.createACard(packId, front, back);
                
                
            }
            public void saveCard(card cardToBeSaved)
            {
                //saves a card of the pack and updates the database
                dbObj=new dbFlashCards(uid);
                dbObj.saveCard(packId, cardToBeSaved.getCardId(), cardToBeSaved.getFront(),cardToBeSaved.getBack());
                
                
            }
            public void deleteCard(int cardId)
            {
                //deletes a card of the pack from the database
                dbObj=new dbFlashCards(uid);
                dbObj.deleteCard(cardId, packId);
                
                
            }
            

}
