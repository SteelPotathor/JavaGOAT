package com.example.javagoat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Pane panel;

    @FXML
    protected void onMouseClicked() {
        System.out.println("Hello World!");
        System.out.println(panel);
    }

}