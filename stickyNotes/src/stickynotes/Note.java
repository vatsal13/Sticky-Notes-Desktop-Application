package stickynotes;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dilipjain
 */
public class Note 
{
        int noteId;
        int uid;
        Date deadLine=null;
        int xCord;
        int yCord;
        int priority;
        String content;
        noteHandler parentNoteHandler;
        Note(int noteId,int userId,int x,int y,int priority,String content,Date deadLine)
        {
            this.noteId=noteId;
            this.uid=userId;
            this.xCord=x;
            this.yCord=y;
            this.priority=priority;
            this.content=content;
            this.deadLine=deadLine;
            //System.out.println("Succfl created a note "+noteId+" uid "+userId+" x "+x+" y  "+
              //      y+" priority "+priority+" content "+content);
        }
        Note(int userId,int noteId)
        {
            this.noteId=noteId;
            this.uid=userId;
            this.priority=0;
            this.content="0";
            xCord=0;    
            yCord=0;
            this.deadLine=null;
            
        }
        public void setParentNoteHandler(noteHandler parentNoteHandler)
        {
            this.parentNoteHandler=parentNoteHandler;
                    
            
        }
        public Date getDeadLine()
        {
            
            return deadLine;
            
        }
        public void setDeadLine(Date d)
        {
            deadLine=d;
            
            
        }
        public int getNoteId()
        {
            return noteId;   
        
        }
        public int getUserId()
        {
         return uid;   
        
        }
        public int getXCord()
        {
            return xCord;   
        }
        public int getYCord()
        {
            return yCord;   
        
        }
        public int getPriority()
        {
         return priority;   
        
        }
        public String getContent()
        {
            return content;   
        
        }
        public void setNoteId(int x)
        {
            noteId=x;   
        
        }
        public void setUserId(int x)
        {
            uid=x;   
        
        }
        public void setXCord(int x)
        {
            xCord=x;   
    
        }
        public void setYCord(int y)
        {
            yCord=y;   
        
        }
        public void setPriority(int x)
        {
            priority=x;   
        
        }
        public void setContent(String c)
        {
            content=c;   
        
        }
        public void intialize()
        {
            setPriority(getPriority());
            setContent(getContent());
            
        }
    
}



