package com.example.javagoat.back;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static com.example.javagoat.back.ModelProfile.profileHashMap;

public class ProfileTableViewHistoric {

    public int id;
    public int priority;
    public ImageView imageView;
    public String firstname;
    public String lastname;
    public int age;
    public String gender;
    public HBox actions;
    public Pane modify;
    public Pane match;
    public boolean firstImage = true;
    public boolean secondImage = true;
    public final ImageView thumbsUp = new ImageView(new Image("file:src/main/resources/com/example/javagoat/pouce-vers-le-haut.png"));
    public final ImageView thumbsDown = new ImageView(new Image("file:src/main/resources/com/example/javagoat/pouce-vers-le-bas.png"));

    public ProfileTableViewHistoric(int id, int significance, ImageView imageView, String firstname, String lastname, int age, String gender) {
        this.id = id;
        this.priority = significance;
        this.imageView = imageView;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        thumbsUp.setPreserveRatio(true);
        thumbsUp.setFitHeight(40);
        thumbsDown.setPreserveRatio(true);
        thumbsDown.setFitHeight(40);
        this.modify = new Pane(thumbsUp);
        this.match = new Pane(thumbsDown);
        this.actions = new HBox(this.modify, this.match);
    }

    public Profile toProfile() {
        return profileHashMap.get(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public HBox getActions() {
        return actions;
    }

    public void setActions(HBox actions) {
        this.actions = actions;
    }

    public Pane getModify() {
        return modify;
    }

    public void setModify(Pane modify) {
        this.modify = modify;
    }

    public Pane getMatch() {
        return match;
    }

    public void setMatch(Pane match) {
        this.match = match;
    }

    public ImageView getThumbsUp() {
        return thumbsUp;
    }

    public ImageView getThumbsDown() {
        return thumbsDown;
    }
}
