package com.example.javagoat.Model;

import com.example.javagoat.Model.matching.ChoiceCriterion;
import com.example.javagoat.Model.matching.Importance;
import com.example.javagoat.Model.matching.RangeCriterion;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * What a person is looking for.
 * <p>
 * Each criterion is either a range (age, size, IQ) or a set of accepted values, and carries an
 * {@link Importance}. The accepted sexes are always eliminatory. A new instance accepts everything
 * with the importance INDIFFERENT, except the sexes, which must be chosen.
 */
public class Preferences {

    public static final RangeCriterion DEFAULT_AGE = new RangeCriterion(18, 99, Importance.INDIFFERENT);
    public static final RangeCriterion DEFAULT_SIZE = new RangeCriterion(140, 210, Importance.INDIFFERENT);
    public static final RangeCriterion DEFAULT_IQ = new RangeCriterion(70, 130, Importance.INDIFFERENT);

    private EnumSet<Biology.sex> sexes = EnumSet.noneOf(Biology.sex.class);
    private RangeCriterion age = DEFAULT_AGE;
    private RangeCriterion size = DEFAULT_SIZE;
    private RangeCriterion iq = DEFAULT_IQ;
    private ChoiceCriterion<PhysicalAttributes.hairColor> hairColor = ChoiceCriterion.indifferent(PhysicalAttributes.hairColor.class);
    private ChoiceCriterion<PhysicalAttributes.hairType> hairType = ChoiceCriterion.indifferent(PhysicalAttributes.hairType.class);
    private ChoiceCriterion<PhysicalAttributes.hairLength> hairLength = ChoiceCriterion.indifferent(PhysicalAttributes.hairLength.class);
    private ChoiceCriterion<Biology.ethnicity> ethnicity = ChoiceCriterion.indifferent(Biology.ethnicity.class);
    private ChoiceCriterion<LifeStyle.bodyBuild> bodyBuild = ChoiceCriterion.indifferent(LifeStyle.bodyBuild.class);
    private ChoiceCriterion<LifeStyle.smoker> smoker = ChoiceCriterion.indifferent(LifeStyle.smoker.class);
    private ChoiceCriterion<LifeStyle.alcohol> alcohol = ChoiceCriterion.indifferent(LifeStyle.alcohol.class);
    private ChoiceCriterion<LifeStyle.athlete> athlete = ChoiceCriterion.indifferent(LifeStyle.athlete.class);
    private ChoiceCriterion<LifeStyle.feed> feed = ChoiceCriterion.indifferent(LifeStyle.feed.class);
    private ChoiceCriterion<LifeStyle.religion> religion = ChoiceCriterion.indifferent(LifeStyle.religion.class);

    public Preferences() {
    }

    public Set<Biology.sex> getSexes() {
        return sexes.clone();
    }

    public void setSexes(Set<Biology.sex> sexes) {
        this.sexes = sexes.isEmpty() ? EnumSet.noneOf(Biology.sex.class) : EnumSet.copyOf(sexes);
    }

    public RangeCriterion getAge() {
        return age;
    }

    public void setAge(RangeCriterion age) {
        this.age = Objects.requireNonNull(age);
    }

    public RangeCriterion getSize() {
        return size;
    }

    public void setSize(RangeCriterion size) {
        this.size = Objects.requireNonNull(size);
    }

    public RangeCriterion getIq() {
        return iq;
    }

    public void setIq(RangeCriterion iq) {
        this.iq = Objects.requireNonNull(iq);
    }

    public ChoiceCriterion<PhysicalAttributes.hairColor> getHairColor() {
        return hairColor;
    }

    public void setHairColor(ChoiceCriterion<PhysicalAttributes.hairColor> hairColor) {
        this.hairColor = Objects.requireNonNull(hairColor);
    }

    public ChoiceCriterion<PhysicalAttributes.hairType> getHairType() {
        return hairType;
    }

    public void setHairType(ChoiceCriterion<PhysicalAttributes.hairType> hairType) {
        this.hairType = Objects.requireNonNull(hairType);
    }

    public ChoiceCriterion<PhysicalAttributes.hairLength> getHairLength() {
        return hairLength;
    }

    public void setHairLength(ChoiceCriterion<PhysicalAttributes.hairLength> hairLength) {
        this.hairLength = Objects.requireNonNull(hairLength);
    }

    public ChoiceCriterion<Biology.ethnicity> getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(ChoiceCriterion<Biology.ethnicity> ethnicity) {
        this.ethnicity = Objects.requireNonNull(ethnicity);
    }

    public ChoiceCriterion<LifeStyle.bodyBuild> getBodyBuild() {
        return bodyBuild;
    }

    public void setBodyBuild(ChoiceCriterion<LifeStyle.bodyBuild> bodyBuild) {
        this.bodyBuild = Objects.requireNonNull(bodyBuild);
    }

    public ChoiceCriterion<LifeStyle.smoker> getSmoker() {
        return smoker;
    }

    public void setSmoker(ChoiceCriterion<LifeStyle.smoker> smoker) {
        this.smoker = Objects.requireNonNull(smoker);
    }

    public ChoiceCriterion<LifeStyle.alcohol> getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(ChoiceCriterion<LifeStyle.alcohol> alcohol) {
        this.alcohol = Objects.requireNonNull(alcohol);
    }

    public ChoiceCriterion<LifeStyle.athlete> getAthlete() {
        return athlete;
    }

    public void setAthlete(ChoiceCriterion<LifeStyle.athlete> athlete) {
        this.athlete = Objects.requireNonNull(athlete);
    }

    public ChoiceCriterion<LifeStyle.feed> getFeed() {
        return feed;
    }

    public void setFeed(ChoiceCriterion<LifeStyle.feed> feed) {
        this.feed = Objects.requireNonNull(feed);
    }

    public ChoiceCriterion<LifeStyle.religion> getReligion() {
        return religion;
    }

    public void setReligion(ChoiceCriterion<LifeStyle.religion> religion) {
        this.religion = Objects.requireNonNull(religion);
    }

    @Override
    public String toString() {
        return "Preferences{sexes=" + sexes + ", age=" + age + ", size=" + size + ", iq=" + iq
                + ", hairColor=" + hairColor + ", hairType=" + hairType + ", hairLength=" + hairLength
                + ", ethnicity=" + ethnicity + ", bodyBuild=" + bodyBuild + ", smoker=" + smoker
                + ", alcohol=" + alcohol + ", athlete=" + athlete + ", feed=" + feed + ", religion=" + religion + '}';
    }
}
