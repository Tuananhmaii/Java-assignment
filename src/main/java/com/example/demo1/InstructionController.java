package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class InstructionController {
    @FXML
    private Button backToMenuButton;

    public void backToMenu() throws IOException{
        Main m = new Main();
        m.changescene("Menu.fxml");
    }
}
