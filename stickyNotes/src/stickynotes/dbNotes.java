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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilipjain
 */
public class dbNotes 
{
        dbConnection connToDB;
        int userId;
        dbNotes(int userId)
        {
            this.userId=userId;
            connToDB=new dbConnection();
        }
        public int addNote()
        {
           try 
            {
                //adding a new Note to database..returns noteId of that note
                Connection c=connToDB.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "insert into Notes(userId) values(?)";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,userId);
                stmt.executeUpdate();
                sql = "select noteId from stickyNotes.Notes where userId=? order by noteId desc";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,userId);
               
                ResultSet rs =stmt.executeQuery();
                if(rs.next())
                {
                       
                          int noteid=rs.getInt("noteId");
                          c.close();
                          //returning noteId if note was successfully created
                          return noteid;
                }
                else
                {
                    c.close();
                    //returnign -1 on failure of creation of window
                    return -1;
                    
                }
                
            }
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        
            
            
        }
        public int deleteNote(int noteId)
        {
           try 
            {
                //delete Note from the database
                //returns 1 if successfully deleted ,-1 on failure
                Connection c=connToDB.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "delete from Notes where noteId=? and userId=?";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,noteId);
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
        public ArrayList<Note> fetchAllNotes()
        {
             try 
            {
                //List of notes
                ArrayList<Note> notesList=new ArrayList<Note>();
               
                Connection c=connToDB.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "select * from stickyNotes.Notes where userId=? order by noteId desc";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,userId);
               
                ResultSet rs =stmt.executeQuery();
                int count=0;
                
                while(rs.next())
                {
                          //fetching notes one by one and adding them to the list
                          System.out.println("Adding "+count+" note to list");
                          Date t;
                          //Handling the date
                          SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                          if(rs.getString("deadLine").equalsIgnoreCase("0"))
                          {
                               t=null;
                              
                          }
                          else
                          {
                                try 
                                { 
                                        //Converting date(string format) fetched from db to Date object
                                        t = ft.parse(rs.getString("deadLine")); 
                                        System.out.println(t); 
                                } 
                                catch (ParseException e) 
                                {
                                        t=null;
                                        //Unable to parse error
                                        System.out.println("Unparseable using " + ft); 
                                }
                          }
                          //Adding note to noteList
                          notesList.add(count,new Note(rs.getInt("noteId"),rs.getInt("userId"),
                                            rs.getInt("x"),rs.getInt("y"),rs.getInt("priority"),
                                            rs.getString("Content"),t));
                          count++;
                          
                          
                }
                c.close();
                //returning List of notes
                return notesList;
            }    
            catch (SQLException ex)
            {
                //returning null if no notes available
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        public int saveANote(Note notesObj)
        {
            try 
            {
                //saving note to the database
                Connection c=connToDB.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                sql = "update Notes set x=?,y=?,priority=?,content=?,deadLine=? where noteId=? and userId=?";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,notesObj.xCord);
                stmt.setInt(2,notesObj.yCord);
                stmt.setInt(3,notesObj.priority);
                stmt.setString(4,notesObj.content);
                stmt.setInt(6,notesObj.noteId);
                stmt.setInt(7,notesObj.uid);
                String date;
                if(notesObj.deadLine==null)
                {
                    date="0";
                    
                }
                else
                {
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    
                    
                    date=ft.format(notesObj.deadLine);
                   
                }
                System.out.println("date is said to be "+date);
                stmt.setString(5,date);
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
