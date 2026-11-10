package com.example.javagoat.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

import static com.example.javagoat.Model.ModelProfile.profileHashMap;

public class TupleHistoHashMap implements Serializable {

    // Frozen value: keeps the existing .dat files readable after code changes.
    private static final long serialVersionUID = 2595076064944729755L;

    public int id;
    public boolean voted;
    public int vote;

    public TupleHistoHashMap() {

    }

    public TupleHistoHashMap(int id) {
        this.id = id;
        this.voted = false;
    }

    public ProfileTableViewHistoric toProfileTableViewHistoric() {
        Profile profile = profileHashMap.get(this.getId());
        ImageView img = new ImageView(ImageLoader.profileImage(profile.getImageView()));
        img.setPreserveRatio(true);
        img.setFitHeight(40);
        img.setFitWidth(40);
        return new ProfileTableViewHistoric(this.getId(), img, profile.getIdentity().getFirstname(), profile.getIdentity().getLastname(), profile.getIdentity().getAge(), profile.getIdentity().getBsex().toString(), this.isVoted(), this.getVote());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TupleHistoHashMap{");
        sb.append("id=").append(id);
        sb.append(", voted=").append(voted);
        sb.append(", vote=").append(vote);
        sb.append('}');
        return sb.toString();
    }

    // A match is identified by the id of the other profile only (voted/vote are mutable state).
    // Without equals(), HashMap.remove(new TupleHistoHashMap(id)) never found anything.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TupleHistoHashMap other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
