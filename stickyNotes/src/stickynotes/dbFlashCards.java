/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilipjain
 */
public class dbFlashCards 
{
        int userId;
        dbConnection connToDb=new dbConnection();
        dbFlashCards(int userId)
        {
            
            this.userId=userId;
            
        }
        public int addPack(String name)
        {
                try 
                {
                    //add a new pack to the database
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "insert into flashCardPack (userId,packname) values (?,?)";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    stmt.setString(2,name);
                    stmt.executeUpdate();
                    c.close();
                    createACard(packExists(name),"Dummy card...Edit it","Place Your Answer");
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            
            
        }
        public int deletePack(String packName)
        {
            try 
                {
                    //delete a pack from the database
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "delete from  flashCardPack where userId=? and packName=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    stmt.setString(2,packName);
                    stmt.executeUpdate();
                    c.close();
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    
                    return -1;
                }
            
            
            
        
        
        
        }
        
        public int  packExists(String name)
        {
                try 
                {
                    //check if a pack exists
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "select packId from flashCardPack where packName=? and userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setString(1,name);
                    stmt.setInt(2,userId);
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        int packId=rs.getInt("packId");
                        c.close();
                       return packId;
                    }
                    else
                    {
                        
                        c.close();
                        return -1;
                    }
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            
            
            
        }
      
        public String[] fetchAllPacks()
        {
                try 
                {
                    //fetching all packs
                    String[] packName=new String[200];
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "select packName from flashCardPack where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    ResultSet rs=stmt.executeQuery();
                    int count=0;
                    while(rs.next())
                    {
                        packName[count]=rs.getString("packName");
                        count++;
                       
                    }
                    c.close();
                    return packName;
                    
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }

            
        }
        public ArrayList<card> fetchAllCards(int packId)
        {
             try 
             {
                 //fetching all cards
                ArrayList<card> cardList=new ArrayList<card>();
               
                Connection c=connToDb.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "select * from flashCard where userId=? and packId=? order by cardId desc";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,userId);
                stmt.setInt(2,packId);
                ResultSet rs =stmt.executeQuery();
                int count=0;
                
                while(rs.next())
                {
                          System.out.println("Adding "+count+" card to list");  
                          cardList.add(count,new card(rs.getInt("cardId"),rs.getInt("packId"),
                                            rs.getInt("userId"),rs.getString("front"),
                                            rs.getString("back")));
                          count++;
                          
                          
                }
                c.close();
                return cardList;
            }    
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            
            
        }
        public int deleteCard(int cardId,int packId)
        {
            try 
                {
                    //deleting a card from database
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "delete from  flashCard where userId=? and packId=? and cardId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    stmt.setInt(2,packId);
                    stmt.setInt(3,cardId);
                    stmt.executeUpdate();
                    c.close();
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    
                    return -1;
                }
            
            
            
            
            
            
        }
        public int saveCard(int packId,int cardId,String front ,String back)
        {
            try 
            {
                //save a card to database
                Connection c=connToDb.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "update flashCard set front=?,back=? where packId=? and userId=? and cardId=?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1,front);
                stmt.setString(2,back);
                stmt.setInt(3,packId);
                stmt.setInt(4,userId);
                stmt.setInt(5,cardId);
                
                stmt.executeUpdate();
                return 1;
            }
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        
            
            
            
        }
        public int createACard(int packId,String front,String back)
        {
                try 
                {
                    //create a new card and add it to database
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "insert into flashCard (userId,packId,front,back) values (?,?,?,?)";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    stmt.setInt(2,packId);
                    stmt.setString(3,front);
                    stmt.setString(4,back);
                    stmt.executeUpdate();
                    c.close();
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            
        }
        

}
