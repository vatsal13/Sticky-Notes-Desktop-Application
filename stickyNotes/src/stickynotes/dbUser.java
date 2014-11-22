package stickynotes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dilipjain
 */
public class dbUser
{
        dbConnection connToDb;
        
        dbUser()
        {
            connToDb=new dbConnection();
            
            
            
        }
        public int getUid(String mailId,String password)
        {
            //Returns uid of person with given mailId or -1 if doesnt exists
            try 
            {
                Connection c=connToDb.connToDb();
              //  System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                //sql query for getting uid
                sql = "SELECT * FROM stickyNotes.User where User.mail_id=?";
                stmt = c.prepareStatement(sql);
                stmt.setString(1,mailId);
                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                {
                     
                      if(rs.getString("password").equals(password))  
                      {
                          //matching password
                          int userid=rs.getInt("userId");
                          c.close();
                          //returning uid if password matches
                          return userid;
                      }
                      else
                          return -1;
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

        public boolean searchUserByName(String mailId)
        {
            //To know user with this mailId already exists
            //return true if user exists and false if user doesnt exists
            try 
            {
                Connection c=connToDb.connToDb();
                System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql; 
                //sql query to fetch uid of person with given mailId
                sql = "SELECT userId FROM stickyNotes.User where User.mail_id=?";             stmt = c.prepareStatement(sql);
                stmt.setString(1,mailId);
                ResultSet rs = stmt.executeQuery();
               // System.out.println("query to check username performed");
                if(rs.next())
                {
                      
                      if(rs.getInt("userId")!=-1)
                      {
                        //  Checking username taken complete and does exist;
                          return true;
                      
                      } 
                      else
                      {
                          //System.out.println("username not taken");
                          return false;
                      }
                }
                else
                {
                   // User doesnt exist
                    return false;
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        

            
        }
        public int addNewUser(String mailId,String contact,String password)
        {
            //adds new user to db ..if adding successfull returns uid of newly added person ..on failure returns -1
            try 
            {
                Connection c=connToDb.connToDb();
                //System.out.println("Creating statement..."); 
                PreparedStatement stmt; 
                String sql = "INSERT INTO User"
				+ "(mail_id, contact, password) VALUES"
				+ "(?,?,?)";
                stmt = c.prepareStatement(sql);
                stmt.setString(1,mailId);
                stmt.setString(2,contact);
                stmt.setString(3,password);
                stmt.executeUpdate();
                sql = "INSERT INTO Reminder"
				+ "(mailId,userId) VALUES"
				+ "(?,?)";
                System.out.println("Adding new user uid is "+getUid(mailId,password));
                stmt = c.prepareStatement(sql);
                stmt.setString(1,mailId);
                stmt.setInt(2,getUid(mailId,password));
                stmt.executeUpdate();
                sql = "INSERT INTO Properties"
				+ "(userId,name) VALUES"
				+ "(?,?)";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,getUid(mailId,password));
                stmt.setString(2,"notes");
                stmt.executeUpdate();
                sql = "INSERT INTO Properties"
				+ "(userId,name) VALUES"
				+ "(?,?)";
                stmt = c.prepareStatement(sql);
                stmt.setInt(1,getUid(mailId,password));
                stmt.setString(2,"cards");
                stmt.executeUpdate();
               // System.out.println("Inserted successfully");
                if(getUid(mailId,password)!=-1)
                    return getUid(mailId,password);
                else
                    return -1;
            }   
            catch (SQLException ex)
            {
                Logger.getLogger(dbUser.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
            
            
            
        }
        



}
