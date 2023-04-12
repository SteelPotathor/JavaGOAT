package com.example.javagoat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView cross_exit;
    @FXML
    private Pane panel;
    @FXML
    private TableView<String> table;
    @FXML
    void exit_script() {
        System.exit(0);
    }
    @FXML
    protected void dashboardclick() {
        System.out.println("Hello World!");
    }

}