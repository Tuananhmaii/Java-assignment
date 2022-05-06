package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button LoginButton;

    @FXML
    private Label wrongLogin;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField UsernameField;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        if(UsernameField.getText().toString().equals("anhphoncute") && PasswordField.getText().toString().equals("phon4802")){
            m.changescene("Menu.fxml");
        }
        else if (UsernameField.getText().isEmpty() || PasswordField.getText().isEmpty()){
            wrongLogin.setText("Please fill all information");
        }
        else wrongLogin.setText("Wrong username or password");
    }
}