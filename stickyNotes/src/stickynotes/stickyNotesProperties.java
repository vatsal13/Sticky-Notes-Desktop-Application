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
public class stickyNotesProperties extends properties
{
       stickyNotesProperties(int uid,String name)
       {
           super(uid,name);
           
           
       }
       
       public void changeTime(int days)
       {
           //changes no.of days in the database table reminder
           dbProperties obj=new dbProperties(uid);
           obj.setTime(days);
           
           
       }
       public int fetchTime()
       {
           //fetches no.of days in the database table 
           dbProperties obj=new dbProperties(uid);
           return obj.getTime();
           
       }
       public String fetchMailId()
       {
           dbProperties obj=new dbProperties(uid);
           return obj.getMailId();
           
       }
       public void changeMailIdForNotification(String mailId)
       {
           dbProperties obj=new dbProperties(uid);
           obj.setMailId(mailId);
           
       }
       public void setNotification(int status)
       {
           dbProperties obj=new dbProperties(uid);
           obj.setStatus(status);
           
           
       }
       
       public int fetchNotification()
       {
           dbProperties obj=new dbProperties(uid);
           return obj.getStatus();
           
       }
       
       
               
}
