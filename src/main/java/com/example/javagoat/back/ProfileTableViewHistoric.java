package com.example.javagoat.back;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static com.example.javagoat.back.ModelProfile.profileHashMap;

public class ProfileTableViewHistoric {

    public int id;
    public ImageView imageView;
    public String firstname;
    public String lastname;
    public int age;
    public String gender;
    public HBox actions;
    public Pane positiveRate;
    public Pane negativeRate;
    public boolean voted = false;
    public final ImageView thumbsUp = new ImageView(new Image("file:src/main/resources/com/example/javagoat/likeColor.png"));
    public final ImageView thumbsDown = new ImageView(new Image("file:src/main/resources/com/example/javagoat/dislikeColor.png"));

    public ProfileTableViewHistoric(int id, ImageView imageView, String firstname, String lastname, int age, String gender) {
        this.id = id;
        this.imageView = imageView;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        thumbsUp.setPreserveRatio(true);
        thumbsUp.setFitHeight(40);
        thumbsDown.setPreserveRatio(true);
        thumbsDown.setFitHeight(40);
        this.positiveRate = new Pane(thumbsUp);
        this.negativeRate = new Pane(thumbsDown);
        this.actions = new HBox(this.positiveRate, this.negativeRate);
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

    public Pane getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Pane positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Pane getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(Pane negativeRate) {
        this.negativeRate = negativeRate;
    }

    public ImageView getThumbsUp() {
        return thumbsUp;
    }

    public ImageView getThumbsDown() {
        return thumbsDown;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileTableViewHistoric{");
        sb.append("id=").append(id);
        sb.append(", imageView=").append(imageView);
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", age=").append(age);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", actions=").append(actions);
        sb.append(", positiveRate=").append(positiveRate);
        sb.append(", negativeRate=").append(negativeRate);
        sb.append(", voted=").append(voted);
        sb.append(", thumbsUp=").append(thumbsUp);
        sb.append(", thumbsDown=").append(thumbsDown);
        sb.append('}');
        return sb.toString();
    }
}
