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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilipjain
 */
public class dbProperties
{
            dbConnection connToDb=new dbConnection();
            int userId;
            dbProperties(int userId)
            {
               this.userId=userId; 
                
                
            }
            public int getTime()
            {
                 try 
                {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql = "select timeForNotification from Reminder where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        int time=rs.getInt("timeForNotification");
                        c.close();
                        return time;
                    }
                    
                    return -1;
            }   
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
         }
            public int setTime(int time)
            {
                try 
                {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "update Reminder set timeForNotification=? where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,time);
                    stmt.setInt(2,userId);
                    stmt.executeUpdate();
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            }
            public int setStatus(int status)
            {
                 try 
                 {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "update Reminder set status=? where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,status);
                    stmt.setInt(2,userId);
                    stmt.executeUpdate();
                    return 1;
                 }
                 catch (SQLException ex)
                 {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                 }   
                
                
            }
            public int getStatus()
            {
                 try 
                {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql = "select status from Reminder where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        int status=rs.getInt("status");
                        c.close();
                        return status;
                    }
                    else
                        return -1;
            }   
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } 
                
                
            }
            public int setMailId(String mailId)
            {
                try 
                 {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "update Reminder set mailId=? where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setString(1,mailId);
                    stmt.setInt(2,userId);
                    stmt.executeUpdate();
                    return 1;
                 }
                 catch (SQLException ex)
                 {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                 }   
             }
            public String getMailId()
            {
                 try 
                 {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql = "select mailId from Reminder where userId=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        String status=rs.getString("mailId");
                        c.close();
                        return status;
                    }
                    else
                        return null;
                    }   
                    catch (SQLException ex)
                    {
                        Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    } 
            
                    
            }
            public Font getFontProperties(String name)
            {
                try 
                {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "select * from Properties where userId=? and name=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1,userId);
                    stmt.setString(2,name);
                    ResultSet rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        Font fontPropObj=new Font(rs.getString("fontFamily"),rs.getInt("fontSize"),rs.getString("fontColor"),rs.getString("name"));
                        return fontPropObj;
                        
                    }    
                    else
                        return null;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
        
                
                
            }
            public int setFontProperties(Font properties)
            {
                try 
                {
                    Connection c=connToDb.connToDb();
                    System.out.println("Creating statement..."); 
                    PreparedStatement stmt; 
                    String sql; 
                    sql = "update Properties  set fontFamily=?,fontSize=?,fontColor=?,name=? where userId=? and name=?";
                    stmt = c.prepareStatement(sql);
                    stmt.setString(1,properties.fontFamily);
                    stmt.setInt(2,properties.fontSize);
                    stmt.setString(3,properties.fontColor);
                    stmt.setString(4,properties.name);
                    stmt.setInt(5,userId);
                    stmt.setString(6, properties.name);
                    stmt.executeUpdate();
                    return 1;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
        
                
            
            
            
            }
            
}
