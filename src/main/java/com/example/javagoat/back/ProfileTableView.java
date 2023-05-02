package com.example.javagoat.back;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ProfileTableView {

    public int id;
    public int priority;
    public ImageView imageView;
    public String firstname;
    public String lastname;
    public int age;
    public String gender;
    public HBox actions;
    public Button modify;
    public Button match;


    public ProfileTableView(int id, int significance, ImageView imageView, String firstname, String lastname, int age, String gender) {
        this.id = id;
        this.priority = significance;
        this.imageView = imageView;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.modify = new Button("Modify");
        this.match = new Button("Match");
        this.actions = new HBox(this.modify, this.match);
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

    public Button getModify() {
        return modify;
    }

    public void setModify(Button modify) {
        this.modify = modify;
    }

    public Button getMatch() {
        return match;
    }

    public void setMatch(Button match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "ProfileTableView{" +
                "id=" + id +
                ", priority=" + priority +
                ", imageView=" + imageView +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", hBox=" + actions +
                ", modify=" + modify +
                ", match=" + match +
                '}';
    }
}
