package com.example.javagoat.back;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import static com.example.javagoat.back.ModelProfile.profileHashMap;

public class ProfileTableView {

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
    public final ImageView logoModif = new ImageView(new Image("file:src/main/resources/com/example/javagoat/reglage.png"));
    public final ImageView logoMatch = new ImageView(new Image("file:src/main/resources/com/example/javagoat/aimer.png"));


    public ProfileTableView(int id, int significance, ImageView imageView, String firstname, String lastname, int age, String gender) {
        this.id = id;
        this.priority = significance;
        this.imageView = imageView;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        logoModif.setPreserveRatio(true);
        logoModif.setFitHeight(40);
        logoMatch.setPreserveRatio(true);
        logoMatch.setFitHeight(40);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        Region region3 = new Region();
        HBox.setHgrow(region3, Priority.ALWAYS);
        this.modify = new Pane(logoModif);
        this.match = new Pane(logoMatch);
        this.actions = new HBox(region1, this.modify, region2, this.match, region3);
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

    public ImageView getLogoModif() {
        return logoModif;
    }

    public ImageView getLogoMatch() {
        return logoMatch;
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
