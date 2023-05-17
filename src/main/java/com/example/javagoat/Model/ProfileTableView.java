package com.example.javagoat.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

import static com.example.javagoat.Model.ModelProfile.profileHashMap;

public class ProfileTableView {

    public final ImageView logoModif = new ImageView(new Image("file:src/main/resources/com/example/javagoat/GlobalImages/reglage.png"));
    public final ImageView logoMatch = new ImageView(new Image("file:src/main/resources/com/example/javagoat/GlobalImages/aimer.png"));
    public int id;
    public HBox priority;
    public HBox imageView;
    public String firstname;
    public String lastname;
    public int age;
    public String gender;
    public HBox actions;
    public Pane modify;
    public Pane match;


    public ProfileTableView(int id, int significance, ImageView imageView, String firstname, String lastname, int age, String gender) {
        this.id = id;

        Region region1Prio = new Region();
        HBox.setHgrow(region1Prio, Priority.ALWAYS);
        Region region2Prio = new Region();
        HBox.setHgrow(region2Prio, Priority.ALWAYS);
        Circle circle;
        switch (significance) {
            case 1 -> circle = new Circle(15, javafx.scene.paint.Color.GREEN);
            case 2 -> circle = new Circle(15, javafx.scene.paint.Color.ORANGE);
            case 3 -> circle = new Circle(15, javafx.scene.paint.Color.RED);
            default -> circle = new Circle(15, javafx.scene.paint.Color.BLACK);
        }
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        this.priority = new HBox(region1Prio, circle, region2Prio);

        Region region1Img = new Region();
        HBox.setHgrow(region1Img, Priority.ALWAYS);
        Region region2Img = new Region();
        HBox.setHgrow(region2Img, Priority.ALWAYS);
        this.imageView = new HBox(region1Img, imageView, region2Img);

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

    public ProfileTableView() {

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

    public HBox getPriority() {
        return priority;
    }

    public void setPriority(HBox priority) {
        this.priority = priority;
    }

    public HBox getImageView() {
        return imageView;
    }

    public void setImageView(HBox imageView) {
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
        final StringBuilder sb = new StringBuilder("ProfileTableView{");
        sb.append("logoModif=").append(logoModif);
        sb.append(", logoMatch=").append(logoMatch);
        sb.append(", id=").append(id);
        sb.append(", priority=").append(priority);
        sb.append(", imageView=").append(imageView);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", age=").append(age);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", actions=").append(actions);
        sb.append(", modify=").append(modify);
        sb.append(", match=").append(match);
        sb.append('}');
        return sb.toString();
    }
}
