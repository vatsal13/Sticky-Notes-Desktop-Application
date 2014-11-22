/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;


import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
//GUI for displaying notes
public class Notes extends AnchorPane
{
        Stage stage;
        int stageNum;
        int uid;
        Double dragDeltaX;
        Double dragDeltaY;
        String color;
        String style;
        noteHandler parentHandler;
        Note note;
        @FXML private HBox deadLineBox;
        public DatePicker deadLineDatePicker;
        @FXML private Pane menuPane;
        @FXML private Pane priorityPane;
        @FXML private Pane anchorPane;
        @FXML private Pane errorMessagePane;
        public TextArea inputContent;
        public Label errorMessage;
        public Notes(Stage stage,int uid,noteHandler parentHandler,Note note,int stageNumber) 
        {
            this.stage=stage;
            this.uid=uid;
            this.parentHandler=parentHandler;
            this.note=note;
            this.stageNum=stageNumber;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("note.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            //inputContent.setText(note.content);
            try 
            {
                System.out.println("loading note");
                fxmlLoader.load();
                //adding datepicker to the note
                loadDate();
                
                System.out.println("  x nd y of stage are "+stage.getX()+" "+stage.getY());
                
                if(note.xCord!=0&&note.yCord!=0)
                {
                    //setting coordinates of note i.e place where it is to be displayed
                    stage.setX(note.xCord);
                    stage.setY(note.yCord);
                }
                System.out.println("  x nd y of stage after changing "+stage.getX()+" "+stage.getY());
                
                if(note.getDeadLine()==null)
                {
                    //if deadLine is null do nothing
                    
                }
                else
                    
                {
                    //if deadLine exists for the note then set that date in the datepicker 
                    System.out.println("Date is "+note.deadLine.toString());
                    deadLineDatePicker.setSelectedDate(note.getDeadLine());
                    
                }
                //hiding save,delete,new,deadLine picker button
                menuPane.setVisible(false);
                priorityPane.setVisible(false);
                deadLineBox.setVisible(false);
                inputContent.setLayoutX(0);
                inputContent.setLayoutY(0);
                inputContent.setPrefHeight(320.0);
                errorMessage.setStyle("-fx-font-size:12;-fx-text-fill:red");
                
                inputContent.setText(note.content);
                //changing color of note based on its priority
                if(note.priority==0)
                {
                    
                    System.out.println("priority is "+note.priority);   
                    color="rgb(255,212,102)";
                }
                else if(note.priority==1)
                {
                    System.out.println("priority is "+note.priority);
                    color="rgb(16,208,0)";
                }
                else
                {
                    System.out.println("priority is "+note.priority);
                    color="rgb(223,223,223)";
                }
                //fetching font properties of note and setting them for the note
                dbProperties properties=new dbProperties(uid);
                Font font=properties.getFontProperties("notes");
                style="-fx-text-fill:"+font.fontColor+";";
                style+="-fx-font-family:"+font.fontFamily+";";
                style+="-fx-font-size:"+font.fontSize+";";
                inputContent.setStyle(style+" -fx-background-color:"+color+";");
            }    
            catch (IOException exception)
            {
                throw new RuntimeException(exception);
            }
    
        }
        
        public void loadDate()
        {
            //Logic for adding deadLine picker i.e datePicker to the note
             deadLineDatePicker = new DatePicker(Locale.ENGLISH);
             deadLineDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
             deadLineDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
             deadLineDatePicker.getCalendarView().setShowWeeks(false);
             deadLineDatePicker.getStylesheets().add("stickynotes/DatePicker.css");
             Label labelForDeadLine=new Label("Deadline:    ");
             labelForDeadLine.setStyle("-fx-font-size:14;-fx-text-fill:red");
             labelForDeadLine.setMinHeight(24);
             labelForDeadLine.setAlignment(Pos.CENTER);
             deadLineBox.getChildren().add(labelForDeadLine);
             deadLineBox.getChildren().add(deadLineDatePicker);
             errorMessage=new Label("");
             errorMessagePane.getChildren().add(errorMessage);
        }
        @FXML protected void onSaveClicked(MouseEvent event) throws Exception
        {
             //When the save button on the note is clicked
            //fetching note's content ,priority,deadLine,x and y coordinate and updating it
             note.content=inputContent.getText();
             note.xCord=(int)stage.getX();
             note.yCord=(int)stage.getY();
             Date d=deadLineDatePicker.getSelectedDate();
             //Logic to handle date
             if(d==null)
             {
                 System.out.println("date was found to be null");
                 
                 note.setDeadLine(null);
             }
             else
             {
                 if(!d.before(new Date()))
                 {
                     errorMessage.setText("");
                    System.out.println("date was found to be "+d.toString());
                    
                    note.setDeadLine(d);
                 }
                 else
                 {
                     //if deadLine chosen is a date previous than current date then display invalid deadLine
                     errorMessage.setText("Invalid DeadLine");
                     errorMessagePane.setVisible(true);
                     note.setDeadLine(null);
                 }
             }
             parentHandler.updateNote(note);
             
        }
        
        @FXML protected void onNewClicked(MouseEvent event) throws Exception
        {
                //creating a new note and setting itz x and y
                System.out.println("Calling create note of parentHandler");
                parentHandler.createNote(note.xCord,note.yCord);


        }
        
        @FXML protected void onDeleteClicked(MouseEvent event) throws Exception
        {
               
               int indexOfDeletingNote=parentHandler.stageList.indexOf(stage);
               System.out.println(" index of deleting note"+indexOfDeletingNote);
               stage.hide();
               //removing note from screen
               parentHandler.removeNoteFromScreen(note);
               //deleting it from notes List and stage List
               parentHandler.noteList.remove(indexOfDeletingNote);
               parentHandler.stageList.remove(indexOfDeletingNote);
               dbNotes obj=new dbNotes(uid);
               //deleting from database
               obj.deleteNote(note.noteId);
                

        }
        @FXML protected void onCloseClicked(MouseEvent event) throws Exception
        {
                //fetching current coordinates of note and updating it
                note.xCord=(int)stage.getX();
                note.yCord=(int)stage.getY();
                //hiding note from screen
                stage.hide();
            
        }
        @FXML protected void onRedClicked(MouseEvent event) throws Exception
        {
                  note.priority=0;
                  //changing note priority to color yellow
                  inputContent.setStyle(style+"-fx-background-color: rgb(255,212,102);");
        }
        @FXML protected void onBlueClicked(MouseEvent event) throws Exception
        {
                note.priority=2;
                //changing note priority to color green
                inputContent.setStyle(style+"-fx-background-color: rgb(223,223,223);");
        }
        @FXML protected void onGreenClicked(MouseEvent event) throws Exception
        {
                 note.priority=1;
                 //changing note priority to color grey
                 inputContent.setStyle(style+"-fx-background-color: rgb(16,208,0);");
        }
        @FXML protected void onTextAreaEntered(MouseEvent event) throws Exception
        {
                //displaying save,delete,new,deadLine picker buttons
                inputContent.setLayoutX(0);
                inputContent.setLayoutY(33);
                inputContent.setPrefHeight(217.0);
               menuPane.setVisible(true);
               priorityPane.setVisible(true);
               deadLineBox.setVisible(true);
               errorMessagePane.setVisible(false);
            
            
        }
        @FXML protected void onTextAreaExited(MouseEvent event) throws Exception
        {
               //hiding save,delete,new deadLine picker buttons 
               menuPane.setVisible(false);
               priorityPane.setVisible(false);
               deadLineBox.setVisible(false);
               errorMessagePane.setVisible(false);
               inputContent.setLayoutX(0);
               inputContent.setLayoutY(0);
               inputContent.setPrefHeight(320.0); 
            
        }

        
        
        
}
