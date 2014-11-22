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
public class users 
{
            int userId;
            String userName;
            String password;
        public void setUid(int userId)
        {
            
            this.userId=userId;
            
        }
        public void setUserName(String userName)
        {
            
            this.userName=userName;
            
        }
        public void setPassword(String password)
        {
            
            this.password=password;
            
        }
        public String validate(String username,String password)
        {
            //Checking if any of the fields is empty
            if(username.length()==0||password.length()==0)
            {
                return "All Fields Required*";
            }
            else
            {
                //Checking if password length is less than 8
                if(password.length()<8)
                {
                    
                    return "Invalid Password";
                }
                else
                {
                   // Checking if the password satisfies any 3 out of 4 condition
                   //Condition1-atleast one digit
                   //Condition2-atleast one Capital Letter
                   //Condition3-atleast one Small letter
                   //Condition4-atleast one out of these 3 ?,%,*
                    int index=0;
                    int capsCount=0;
                    int digitCount=0;
                    int smallCount=0;
                    int specialCount=0;
                    while(index<password.length())
                    {
                        char c=password.charAt(index);
                        if(c>=65&&c<97)
                            capsCount++;
                        if(c>=97&&c<=122)
                            smallCount++;
                        if(c>=48&&c<=57)
                            digitCount++;
                        if(c=='?'||c=='%'||c=='*')
                            specialCount++;
                         index++;
                    }
                    if(capsCount>0)
                        capsCount=1;
                    if(smallCount>0)
                        smallCount=1;
                    if(digitCount>0)
                        digitCount=1;
                    if(specialCount>0)
                        specialCount=1;
                    int counter=capsCount+specialCount+smallCount+digitCount;
                    System.out.println("count is "+counter);
                    if(counter<3)
                        return "Invalid password";
                    else
                    {
                       // All Password Criteria met returning null in errorMessage;
                            return null;
                        
                    }
                }
                
             } 
         }


        public int verifyCredentials(String username,String password)
        {
            
            //Verifying the entered username and password with database returns -1 if didnt match
            dbUser dbAccess=new dbUser();
            return dbAccess.getUid(username, password);
        }
        
        public int registerUser(String username,String contact,String password)
        {
            dbUser dbuser=new dbUser();
            
            if(dbuser.searchUserByName(username))
            {
              //Checking if user with this mail id already exists if yes returning -1 else proceeding with registration
                return -1;
                
            }
            else
            {
                //Adding new user to database and returning uid
                int uid=dbuser.addNewUser(username, contact, password);
                //System.out.println("adding finished");
                return  uid;
                
            }
            
            
        }        
           

}
