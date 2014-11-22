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
//GUI for login Screen
/**
 *
 * @author dilipjain
 */
public class LoginController extends AnchorPane 
{
    Stage stage;
    @FXML private Label errorMessage;
    @FXML private PasswordField loginPassword;
    @FXML private TextField loginUserName;
    public LoginController(Stage stage)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.stage=stage;
        
        try 
        {
            fxmlLoader.load();
            
            errorMessage.textProperty().set("");
        } 
        catch (IOException exception)
        {
            System.out.println("Ran itno error");
            throw new RuntimeException(exception);
        }
    
    }
    
    
    @FXML protected void onRegisterLinkClicked(MouseEvent event) throws Exception {
        //On clicking of registeration link loading register scene
        stage.setScene(new Scene(new RegisterController(stage)));
        stage.setTitle("Sticky Notes");
        stage.show();
    
    
    }
     
    @FXML protected void onLoginButtonClicked(ActionEvent event) throws Exception 
    {
       //On Click of Login Button 
        errorMessage.textProperty().set("");
        users user=new users();
        //fetching username and password
        String username=loginUserName.textProperty().get();
        String password=loginPassword.textProperty().get();
        String result=user.validate(username,password);
       // System.out.println("got result");
            if(result!=null)
            {    
               // Displaying error Message for invalid password or if any field is empty 
                errorMessage.textProperty().set(result);
            }
            else
            {
               //Validating username and password with database
                int uid=user.verifyCredentials(username, password);
               // Uid=-1 signifies credentials donot match displaying necessary error message
                if(uid==-1)
                    errorMessage.textProperty().set("Credentials do not match");
                else
                {
                    //Login Successful Loading users HomeScreen
                   stage.setScene(new Scene(new HomeScreenController(stage,uid)));
                   stage.setTitle("Home");
                   stage.show(); 
                }
            }
    
          
        }
}