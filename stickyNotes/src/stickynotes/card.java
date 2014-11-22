/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

/**
 *
 * @author dilipjain
 */
public class card 
{
        int count;
        private int cardId;
        private int packId;
        private int userId;
        private String front;
        private String back;
        card(int cardId,int packId,int userId)
        {
            count=0;
            this.cardId=cardId;
            this.packId=packId;
            this.userId=userId;
            
            
        }
        card(int cardId,int packId,int userId,String front,String back)
        {
            count=0;
            this.cardId=cardId;
            this.packId=packId;
            this.userId=userId;
            this.front=front;
            this.back=back;
            
        }
        public void setFront(String front)
        {
            this.front=front;
            
            
        }
        public void setBack(String back)
        {
            this.back=back;
            
            
        }
        public void setCardId(int id)
        {
            this.cardId=id;
            
            
        }
        public void setUserId(int id)
        {
            this.userId=id;
            
            
        }
        public void setPackId(int id)
        {
            this.packId=id;
            
            
        }
        public int getPackId()
        {
            
            return packId;
        }
        public int getCardId()
        {
            
            return cardId;
        }
        public int getUserId()
        {
            
            return userId;
        }
        public String getFront()
        {
            
            return front;
        }
        public String getBack()
        {
            
            return back;
        }
        public void initialize()
        {
            System.out.println("The card has been initialized");
            count++;
            
        }


}
