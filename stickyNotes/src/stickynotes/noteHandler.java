/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class noteHandler 
{
        //parent of all notes being displayed on the screen
        int uid;
        Stage parentStage;
        int stageCount;
        //list of all stages used by all notes
        ArrayList<Stage> stageList=new ArrayList<Stage>();
        //list of all notes being displayed
        ArrayList<Note> noteList=new ArrayList<Note>();
        noteHandler(Stage parentStage,int uid)
        {
            this.parentStage=parentStage;
            this.uid=uid;
            this.stageCount=0;
        }
        public void createNote(int x,int y)
        {
            // Creating a new note
            //adding new note to database
            // x nd y are coordinates of parent
            dbNotes obj=new dbNotes(uid);
            //receiving noteId of the new created note
            int noteId=obj.addNote();
            System.out.println("Noteid is "+noteId);
            //creating note object
            Note note=new Note(uid,noteId);
            note.intialize();
            note.setParentNoteHandler(this);
            note.setXCord(x+10);
            note.setYCord(y+10);
            //adding new note to screen
            addNoteToScreen(note);
            
            
        }
        public void displayNotes()
        {
                //initially hiding  notes currently being displayed on screen
                hideNotes();
                dbNotes dbObj=new dbNotes(uid);
                //Reinitializing stages for the notes
                stageList=new ArrayList<Stage>();
                //Reinitializing notes list of the notes 
                noteList=new ArrayList<Note>();
                stageCount=0;
                ArrayList<Note> notesList;
                System.out.println("fetching notes from db");
                //fetching notes from database
                notesList=dbObj.fetchAllNotes();
                System.out.println("fetched notes from db and arry length is "+notesList.size());
                //iterating over the list of notes and displaying them one by one
                 for(int i=0;i<notesList.size();i++)
                 {
                        addNoteToScreen(notesList.get(i));
                        System.out.println("added note "+i+" to display"+"itz x nd y are "+notesList.get(i).xCord+" "+notesList.get(i).yCord);
                        
                 }
                 //if the user has no notes by default a note is created for him and displayed
                 if(notesList.size()==0)
                 {
                     System.out.println("Since no.of notes are zero createing a new note with userId "+uid);
                     Note note=new Note(uid,dbObj.addNote());
                     addNoteToScreen(note);
                     
                     
                 }
                
            
        }
        public void updateNote(Note note)
        {
             //Saves or updates the note to the database
             dbNotes obj=new dbNotes(uid);
             obj.saveANote(note);

        }
        public void hideNotes()
        {
            System.out.println("No.of notes is "+noteList.size());
            //fetching iterator to iterate over the list of notes being displayed
            Iterator<Note> noted= noteList.iterator();
            while(noted.hasNext())
            {
                //fetch a note that is being displated
                Note tempNote=noted.next();
                int index=noteList.indexOf(tempNote);
                Stage s=stageList.get(index);
                //update the notes x and y coordinates of the note
                tempNote.xCord=(int)s.getX();
                tempNote.yCord=(int)s.getY();
               
                //update the note in database
                updateNote(tempNote);
                
            }
            //hiding note one by one
            Iterator<Stage> s= stageList.iterator();
            while(s.hasNext())
            {
                //fetches the note
                Stage stageRemove=s.next();
                //hides the note
                stageRemove.hide();
                
            }
            
        }
        public void addNoteToScreen(Note note)
        {
            //creating stage for the note
            Stage s=new Stage();
            //adding note to the noteList
            noteList.add(stageCount, note);
            //adding stage to stageList
            stageList.add(stageCount,s);
            stageCount++;
            System.out.println("stage created for the note");
            stageList.get(stageCount-1).setScene(new Scene(new Notes(stageList.get(stageCount-1),uid,this,noteList.get(stageCount-1),stageCount-1)));
            System.out.println("scene set created for the note and stage no is "+(stageCount-1));
            stageList.get(stageCount-1).setTitle("");
            //displaying note on screen
            stageList.get(stageCount-1).show();
    
        }
        public void removeNoteFromScreen(Note note)
        {
            //removing Note From Screen
            System.out.println("Note being removed");
            
            
            
        }
        


}
