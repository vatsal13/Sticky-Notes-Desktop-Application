/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class PropertiesController extends AnchorPane 
{
        Stage stage;
        int uid;
        
        @FXML private ChoiceBox noteFamily;
        @FXML private ChoiceBox noteSize;
        @FXML private ColorPicker noteColor;
        @FXML private ChoiceBox cardFamily;
        @FXML private ChoiceBox cardSize;
        @FXML private ColorPicker cardColor;
        @FXML private ChoiceBox noteTime;
        @FXML private TextField noteMailId;
        @FXML private CheckBox noteStatus;
        public PropertiesController(Stage stage,int uid) 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("properties.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            this.uid=uid;
            this.stage=stage;
            try 
            {
                fxmlLoader.load();
                //stage.setMaxWidth(600.0);
                //stage.setMaxHeight(452.0);
                initialize();
            }    
            catch (IOException exception)
            {
                throw new RuntimeException(exception);
            }
    
        }
        private void initialize()
        {
                //setting view of the properties scene
                //setting font of notes with list of permisible values of font family
                noteFamily.setItems(FXCollections.observableArrayList(getFont()));
                //setting font of cards with list of permisible values of font family
                cardFamily.setItems(FXCollections.observableArrayList(getFont()));
                //setting font size of notes with list of permisible values of size
                noteSize.setItems(FXCollections.observableArrayList(getSize()));
                //setting font size of cards with list of permisible values of size
                cardSize.setItems(FXCollections.observableArrayList(getSize()));
                //setting time of notes with list of permisible values of days
                noteTime.setItems(FXCollections.observableArrayList(getDays()));
                stickyNotesProperties notesProperties=new stickyNotesProperties(uid,"notes");
                //setting enable/disable notification as stored in properties table
                if(notesProperties.fetchNotification()==0)
                    noteStatus.setSelected(false);
                else
                    noteStatus.setSelected(true);
                //setting mailId which is stored in properties table
                noteMailId.setText(notesProperties.fetchMailId());
                //setting days as stored in properties table
                int index=getDays().indexOf(notesProperties.fetchTime());
                if(index!=-1)
                    noteTime.getSelectionModel().select(index);
                //setting font family for notes as stored in properties table
                index=getFont().indexOf(notesProperties.fetchFontProperties().fontFamily);
                if(index!=-1)
                    noteFamily.getSelectionModel().select(index);
                System.out.println("note size is "+notesProperties.fetchFontProperties().fontFamily);
                //setting font size for notes as stored in properties table
                index=getSize().indexOf(notesProperties.fetchFontProperties().fontSize);
                System.out.println(" index of notes is "+index);
                if(index!=-1)
                    noteSize.getSelectionModel().select(index);
                //setting font color for notes as stored in properties table
                String color=notesProperties.fetchFontProperties().getFontColor();
                javafx.scene.paint.Color c=null;
                //handling color to be displayed---gui logic
                if(color.contains("rgb"))
                {
                    
                    color=color.substring(4, color.length()-1);
                    String[] s=color.split(",");
                    if(s.length>=3);
                    {
                            double red=Integer.parseInt(s[0])/255.0;
                            double green=Integer.parseInt(s[1])/255.0;
                            double blue=Integer.parseInt(s[2])/255.0;
                            c=
                                javafx.scene.paint.Color.color(red, green,blue);
                    }
                }
                
                if(c!=null)
                    noteColor.setValue(c);
                flashCardProperties objFlash=new flashCardProperties(uid,"cards");
                //getting font family selected by user for cards
                index=getFont().indexOf(objFlash.fetchFontProperties().fontFamily);
                if(index!=-1)
                    cardFamily.getSelectionModel().select(index);
                //getting font size selected by user for font family
                index=getSize().indexOf(objFlash.fetchFontProperties().fontSize);
                if(index!=-1)
                    cardSize.getSelectionModel().select(index);
                //getting font color selected by user for font family
                color=objFlash.fetchFontProperties().getFontColor();
                c=null;
                //color display logic---gui logic using rgb values to create color object
                if(color.contains("rgb"))
                {
                    
                    color=color.substring(4, color.length()-1);
                    String[] s=color.split(",");
                    if(s.length>=3);
                    {
                            double red=Integer.parseInt(s[0])/255.0;
                            double green=Integer.parseInt(s[1])/255.0;
                            double blue=Integer.parseInt(s[2])/255.0;
                            c=
                                javafx.scene.paint.Color.color(red, green,blue);
                    }
                }
                
                if(c!=null)
                    cardColor.setValue(c);
                
            
            
        }
       private ArrayList<String> getFont()
       {
         //Returns List of all font families your system supports
         GraphicsEnvironment graphicsEnvironment =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
         String fontNames[] = graphicsEnvironment.getAvailableFontFamilyNames();
         ArrayList<String> stringList=new ArrayList<String>();
         for(int i=0;i<fontNames.length;i++)
         {
             stringList.add(fontNames[i]);
            
         }
         return stringList;
      }
      private ArrayList<Integer> getSize()
       {
         //returns list all possible size of font allowed by application  
         ArrayList<Integer> sizeList=new ArrayList<Integer>();
         for(int i=12;i<=24;i+=2)
         {
             sizeList.add(i);
            
         }
         return sizeList;
      }
      private ArrayList<Integer> getDays()
      {
          //returns list of all possible days allowed by application
         ArrayList<Integer> daysList=new ArrayList<Integer>();
         for(int i=1;i<=7;i++)
         {
             daysList.add(i);
            
         }
         System.out.println("size of list is"+daysList.size());
         return daysList;
      }
      
      @FXML protected void onSaveClicked(MouseEvent event) throws Exception
      {
          
            ArrayList<String> fontList=getFont();
            ArrayList<Integer> sizeList=getSize();
            ArrayList<Integer> dayList=getDays();
            
            stickyNotesProperties obj=new stickyNotesProperties(uid,"notes");
            //fetching font properties of notes
            Font font=new Font("notes");
            //fetching font family selected by user for the notes
            int noteFamilyIndex=noteFamily.getSelectionModel().getSelectedIndex();
            //fetching font size selected by user for the notes
            int noteFontSizeIndex=noteSize.getSelectionModel().getSelectedIndex();
            //fetching font color selected by user for the notes
            javafx.scene.paint.Color c=noteColor.getValue();
            String color="rgb("+(int)(c.getRed()*255)+","+(int)(c.getGreen()*255)+","+(int)(c.getBlue()*255)+")";
            if(noteFamilyIndex!=-1)
                font.setfontFamily(fontList.get(noteFamilyIndex));
            if(noteFontSizeIndex!=-1)
                font.setFontSize(sizeList.get(noteFontSizeIndex));
            font.setFontColor(color);
            //fetching mailId for notification set by user 
            String mailId=noteMailId.getText();
            //fetching no.of days set by user 
            int timeIndex=noteTime.getSelectionModel().getSelectedIndex();
            //fetching status of enable/disable notification of user
            BooleanProperty status=noteStatus.selectedProperty();
            //updating time in database
            obj.changeTime(dayList.get(timeIndex));
            //updating  mailId in database
            obj.changeMailIdForNotification(mailId);
            if(status.getValue())
            {
                //updating status in database
                obj.setNotification(1);
                
            }
            else
                obj.setNotification(0);
            
            //fetching flashcards properties
            flashCardProperties flashCardObj=new flashCardProperties(uid,"cards");
            Font fontForCards=new Font("cards");
            //fetching font family values permitted
            int cardFamilyIndex=cardFamily.getSelectionModel().getSelectedIndex();
            //fetching font size 
            int cardFontSizeIndex=cardSize.getSelectionModel().getSelectedIndex();
            javafx.scene.paint.Color c1=cardColor.getValue();
            String color1="rgb("+(int)(c1.getRed()*255)+","+(int)(c1.getGreen()*255)+","+(int)(c1.getBlue()*255)+")";
            //updating font family of the cards
            if(cardFamilyIndex!=-1)
                fontForCards.setfontFamily(fontList.get(cardFamilyIndex));
            //updating font size of the cards
            if(cardFontSizeIndex!=-1)
                fontForCards.setFontSize(sizeList.get(cardFontSizeIndex));
            //updating font color of the cards in database
            fontForCards.setFontColor(color1);
            //updating font properties of the notes in database
            obj.setFontProperties(font);
            //updating font properties of the notes in database
            flashCardObj.setFontProperties(fontForCards);
            stage.setScene(new Scene(new HomeScreenController(stage,uid)));
      }

      @FXML protected void onBackClicked(MouseEvent event) throws Exception
      {
            //displaying homescreen
            stage.setScene(new Scene(new HomeScreenController(stage,uid)));
            
          
          
          
      }        
              
              



}
