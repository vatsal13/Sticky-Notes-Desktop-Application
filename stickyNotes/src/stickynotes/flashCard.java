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
public class flashCard
{
        int userId;
        dbFlashCards dbObj;
        flashCardPack pack;
        flashCard(int userId)
        {
            this.userId=userId;
            
        }
        public int createPack(String name)
        {
            //check if pack with this name already exists
            dbObj=new dbFlashCards(userId);
            if(dbObj.packExists(name)!=-1)
                   return -1;
            else
            {
                //no such pack exists ..creates a new pack and returns its packid
                return dbObj.addPack(name);
                
            }
            
        }
        public String[] fetchPackList()
        {
            //fetches list of all exisitng pack from the database
            dbObj=new dbFlashCards(userId);
            return dbObj.fetchAllPacks();
            
        }
        public int deletePack(String name)
        {
            //deletes a selected pack from the database
            dbObj=new dbFlashCards(userId);
            
            return dbObj.deletePack(name);
            
            
        }
        public boolean isSelectedPackBeingDisplayed()
        {
            System.out.println("Checking if the pack is being displayed");
            fetchPackList();
            return false;
            
        }
        public void hidePack()
        {
            System.out.println("Hiding pack being displayed");
            fetchPackList();
            
            
        }
        public flashCardPack viewPack(String name)
        {
            //creates a pack object..this pack is then displayed on the screen
            dbObj=new dbFlashCards(userId);
            int packId=dbObj.packExists(name);
            pack=new flashCardPack(userId,packId);
            
            return pack;
            
        }
        public ArrayList<card> fetchAllCards(int packId)
        {
            dbObj=new dbFlashCards(userId);
            
            return dbObj.fetchAllCards(packId);
            
            
        }
        public void savePack()
        {
            System.out.println("saving the pack being edited");
            //fetching updated pack list
            fetchPackList();
            
        }
        

}
