/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author dilipjain
 */
public class RegisterController extends AnchorPane 
{
        Stage stage;
    @FXML private Label errorMessage;
    @FXML private TextField registerUsername;
    @FXML private PasswordField registerPassword;
    @FXML private TextField registerContact;
    int uid;
    public RegisterController(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        this.stage=stage;
        try 
        {
            fxmlLoader.load();
            
        } 
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    
    }
    
    
    @FXML protected void onRegisterButtonClicked(ActionEvent event) throws Exception
    {
                //on registration button clicked
                 errorMessage.textProperty().set("");
                 //fetching username password and contact no
                 String username=registerUsername.textProperty().get();
                 String password=registerPassword.textProperty().get();
                 String contact=registerContact.textProperty().get();
                 //validating username password if all condition met result will be null
                 users user=new users();
                 String result=user.validate(username,password);
                 
                 if(result!=null)
                 {    
                     //there was some error in username or password
                     //displaying errorMessage
                     errorMessage.textProperty().set(result);
                 }
                 else
                 {
                        //stat holds the userId 
                        int stat=user.registerUser(username,contact,password);
                        //if stat is -1 there was some error in adding user to database displaying error message
                        if(stat==-1)
                            errorMessage.textProperty().set("User already exists");
                        else
                        {
                              //user successfully registered loading his homescreen
                               uid= stat;
                               stage.setScene(new Scene(new HomeScreenController(stage,uid)));
                               stage.setTitle("Home");
                               stage.show(); 

                        }
                        
                 }
                 
        
        
    
    }
     
    @FXML protected void onSignInClicked(MouseEvent event)
    {
        stage.setScene(new Scene(new LoginController(stage)));
        stage.setTitle("Sticky Notes");
        stage.show();
      
        
    }






}
