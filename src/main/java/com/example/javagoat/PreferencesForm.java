package com.example.javagoat;

import com.example.javagoat.Model.Biology;
import com.example.javagoat.Model.LifeStyle;
import com.example.javagoat.Model.PhysicalAttributes;
import com.example.javagoat.Model.Preferences;
import com.example.javagoat.Model.matching.ChoiceCriterion;
import com.example.javagoat.Model.matching.Importance;
import com.example.javagoat.Model.matching.RangeCriterion;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * The "preferences" part of the new-profile and edit-profile pages, built in code (shared by both pages).
 * <p>
 * Each criterion has an importance (Indifferent / Nice to have / Important / Required) and either a set of
 * accepted values or a min-max range. The three tabs of the pages are filled as follows:
 * lifestyle (smoker, alcohol, athlete, feed, religion), physical (body build, hair, ethnicity) and
 * "passion/other" (sexes looked for, age, size, IQ).
 */
final class PreferencesForm {

    private static final double CELL_WIDTH = 200;
    private static final String CONTROL_STYLE = "-fx-background-radius: 50; -fx-border-radius: 50;";

    private final Runnable onChange;

    private final CheckComboBox<Biology.sex> sexes;
    private final RangeRow age;
    private final RangeRow size;
    private final RangeRow iq;
    private final ChoiceRow<LifeStyle.smoker> smoker;
    private final ChoiceRow<LifeStyle.alcohol> alcohol;
    private final ChoiceRow<LifeStyle.athlete> athlete;
    private final ChoiceRow<LifeStyle.feed> feed;
    private final ChoiceRow<LifeStyle.religion> religion;
    private final ChoiceRow<LifeStyle.bodyBuild> bodyBuild;
    private final ChoiceRow<PhysicalAttributes.hairColor> hairColor;
    private final ChoiceRow<PhysicalAttributes.hairType> hairType;
    private final ChoiceRow<PhysicalAttributes.hairLength> hairLength;
    private final ChoiceRow<Biology.ethnicity> ethnicity;

    /**
     * @param onChange called whenever a value changes (used to refresh the green/red indicators)
     */
    PreferencesForm(Pane lifestylePane, Pane physicalPane, Pane otherPane, Runnable onChange) {
        this.onChange = onChange;

        smoker = new ChoiceRow<>("Smoker", LifeStyle.smoker.class);
        alcohol = new ChoiceRow<>("Alcohol", LifeStyle.alcohol.class);
        athlete = new ChoiceRow<>("Athlete", LifeStyle.athlete.class);
        feed = new ChoiceRow<>("Feed", LifeStyle.feed.class);
        religion = new ChoiceRow<>("Religion", LifeStyle.religion.class);
        fill(lifestylePane, smoker.cell, alcohol.cell, athlete.cell, feed.cell, religion.cell);

        bodyBuild = new ChoiceRow<>("Body build", LifeStyle.bodyBuild.class);
        hairColor = new ChoiceRow<>("Color of hair", PhysicalAttributes.hairColor.class);
        hairType = new ChoiceRow<>("Hair type", PhysicalAttributes.hairType.class);
        hairLength = new ChoiceRow<>("Hair length", PhysicalAttributes.hairLength.class);
        ethnicity = new ChoiceRow<>("Ethnicity", Biology.ethnicity.class);
        fill(physicalPane, bodyBuild.cell, hairColor.cell, hairType.cell, hairLength.cell, ethnicity.cell);

        sexes = checkComboBox(Biology.sex.class);
        Label sexesNote = new Label("(always required)");
        VBox sexesCell = cell("Looking for", sexes, sexesNote);
        age = new RangeRow("Age", FormInput.MIN_AGE, FormInput.MAX_AGE);
        size = new RangeRow("Size (cm)", FormInput.MIN_SIZE, FormInput.MAX_SIZE);
        iq = new RangeRow("IQ", FormInput.MIN_IQ, FormInput.MAX_IQ);
        fill(otherPane, sexesCell, age.cell, size.cell, iq.cell);
    }

    // ------------------------------------------------------------------ data

    void show(Preferences p) {
        sexes.getCheckModel().clearChecks();
        p.getSexes().forEach(s -> sexes.getCheckModel().check(s));
        age.show(p.getAge());
        size.show(p.getSize());
        iq.show(p.getIq());
        smoker.show(p.getSmoker());
        alcohol.show(p.getAlcohol());
        athlete.show(p.getAthlete());
        feed.show(p.getFeed());
        religion.show(p.getReligion());
        bodyBuild.show(p.getBodyBuild());
        hairColor.show(p.getHairColor());
        hairType.show(p.getHairType());
        hairLength.show(p.getHairLength());
        ethnicity.show(p.getEthnicity());
    }

    /** @return the problems of the form, empty when {@link #read()} can be called */
    List<String> problems() {
        List<String> problems = new ArrayList<>();
        problems.addAll(lifestyleProblems());
        problems.addAll(physicalProblems());
        problems.addAll(otherProblems());
        return problems;
    }

    boolean lifestyleValid() {
        return lifestyleProblems().isEmpty();
    }

    boolean physicalValid() {
        return physicalProblems().isEmpty();
    }

    boolean otherValid() {
        return otherProblems().isEmpty();
    }

    private List<String> lifestyleProblems() {
        List<String> problems = new ArrayList<>();
        for (ChoiceRow<?> row : List.of(smoker, alcohol, athlete, feed, religion)) {
            row.check(problems);
        }
        return problems;
    }

    private List<String> physicalProblems() {
        List<String> problems = new ArrayList<>();
        for (ChoiceRow<?> row : List.of(bodyBuild, hairColor, hairType, hairLength, ethnicity)) {
            row.check(problems);
        }
        return problems;
    }

    private List<String> otherProblems() {
        List<String> problems = new ArrayList<>();
        if (sexes.getCheckModel().getCheckedItems().isEmpty()) {
            problems.add("Preferences - Looking for: choose MALE and/or FEMALE");
        }
        age.check(problems);
        size.check(problems);
        iq.check(problems);
        return problems;
    }

    Preferences read() {
        List<String> problems = problems();
        if (!problems.isEmpty()) {
            throw new IllegalStateException(String.join("\n", problems));
        }
        Preferences p = new Preferences();
        p.setSexes(EnumSet.copyOf(sexes.getCheckModel().getCheckedItems()));
        p.setAge(age.read(Preferences.DEFAULT_AGE));
        p.setSize(size.read(Preferences.DEFAULT_SIZE));
        p.setIq(iq.read(Preferences.DEFAULT_IQ));
        p.setSmoker(smoker.read());
        p.setAlcohol(alcohol.read());
        p.setAthlete(athlete.read());
        p.setFeed(feed.read());
        p.setReligion(religion.read());
        p.setBodyBuild(bodyBuild.read());
        p.setHairColor(hairColor.read());
        p.setHairType(hairType.read());
        p.setHairLength(hairLength.read());
        p.setEthnicity(ethnicity.read());
        return p;
    }

    // ------------------------------------------------------------------ rows

    /** Accepted values + importance. */
    private final class ChoiceRow<E extends Enum<E>> {
        final String label;
        final Class<E> type;
        final CheckComboBox<E> values;
        final ChoiceBox<Importance> importance = importanceBox();
        final VBox cell;

        ChoiceRow(String label, Class<E> type) {
            this.label = label;
            this.type = type;
            this.values = checkComboBox(type);
            this.cell = cell(label, values, importance);
        }

        void show(ChoiceCriterion<E> criterion) {
            values.getCheckModel().clearChecks();
            criterion.accepted().forEach(v -> values.getCheckModel().check(v));
            importance.setValue(criterion.importance());
        }

        void check(List<String> problems) {
            if (importance.getValue() != Importance.INDIFFERENT && values.getCheckModel().getCheckedItems().isEmpty()) {
                problems.add("Preferences - " + label + ": check at least one accepted value, or choose 'Indifferent'");
            }
        }

        ChoiceCriterion<E> read() {
            if (importance.getValue() == Importance.INDIFFERENT) {
                return ChoiceCriterion.indifferent(type);
            }
            return new ChoiceCriterion<>(type, List.copyOf(values.getCheckModel().getCheckedItems()), importance.getValue());
        }
    }

    /** Min - max + importance. */
    private final class RangeRow {
        final String label;
        final int lowest;
        final int highest;
        final TextField min = numberField("min");
        final TextField max = numberField("max");
        final ChoiceBox<Importance> importance = importanceBox();
        final VBox cell;

        RangeRow(String label, int lowest, int highest) {
            this.label = label;
            this.lowest = lowest;
            this.highest = highest;
            HBox fields = new HBox(10, min, max);
            this.cell = cell(label + " (" + lowest + "-" + highest + ")", fields, importance);
        }

        void show(RangeCriterion criterion) {
            min.setText(String.valueOf(criterion.min()));
            max.setText(String.valueOf(criterion.max()));
            importance.setValue(criterion.importance());
        }

        void check(List<String> problems) {
            if (importance.getValue() == Importance.INDIFFERENT) {
                return; // the values are ignored
            }
            Integer lo = parse(min.getText());
            Integer hi = parse(max.getText());
            String prefix = "Preferences - " + label + ": ";
            if (lo == null || hi == null) {
                problems.add(prefix + "fill min and max, or choose 'Indifferent'");
            } else if (lo < lowest || hi > highest) {
                problems.add(prefix + "values must be between " + lowest + " and " + highest);
            } else if (lo > hi) {
                problems.add(prefix + "min (" + lo + ") is greater than max (" + hi + ")");
            }
        }

        RangeCriterion read(RangeCriterion defaults) {
            if (importance.getValue() == Importance.INDIFFERENT) {
                Integer lo = parse(min.getText());
                Integer hi = parse(max.getText());
                // Keep what was typed when it is consistent, so that switching back to another importance is easy
                return lo != null && hi != null && lo <= hi
                        ? new RangeCriterion(lo, hi, Importance.INDIFFERENT)
                        : defaults;
            }
            return new RangeCriterion(parse(min.getText()), parse(max.getText()), importance.getValue());
        }

        private Integer parse(String text) {
            try {
                return Integer.parseInt(text.strip());
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    // ------------------------------------------------------------------ widgets

    private VBox cell(String title, javafx.scene.Node main, javafx.scene.Node second) {
        Label label = new Label(title);
        label.setFont(new Font(16));
        VBox box = new VBox(6, label, main, second);
        box.setPrefWidth(CELL_WIDTH);
        return box;
    }

    private static void fill(Pane pane, VBox... cells) {
        GridPane grid = new GridPane();
        grid.setHgap(14);
        grid.setVgap(14);
        grid.setPadding(new Insets(4, 0, 0, 0));
        for (int i = 0; i < cells.length; i++) {
            grid.add(cells[i], i % 3, i / 3);
        }
        pane.getChildren().setAll(grid);
    }

    private <E extends Enum<E>> CheckComboBox<E> checkComboBox(Class<E> type) {
        CheckComboBox<E> box = new CheckComboBox<>();
        box.getItems().setAll(Arrays.asList(type.getEnumConstants()));
        box.setConverter(new StringConverter<>() {
            @Override
            public String toString(E value) {
                return value == null ? "" : value.name();
            }

            @Override
            public E fromString(String text) {
                return Enum.valueOf(type, text);
            }
        });
        box.setTitle("Any");
        box.setShowCheckedCount(false);
        box.setPrefWidth(CELL_WIDTH);
        box.setPrefHeight(32);
        box.setStyle(CONTROL_STYLE);
        box.getCheckModel().getCheckedItems().addListener((ListChangeListener<E>) change -> {
            // The title replaces the list of checked values: show them, or "Any" when none
            box.setTitle(box.getCheckModel().getCheckedItems().isEmpty() ? "Any" : null);
            onChange.run();
        });
        return box;
    }

    private ChoiceBox<Importance> importanceBox() {
        ChoiceBox<Importance> box = new ChoiceBox<>();
        box.getItems().setAll(Importance.values());
        box.setValue(Importance.INDIFFERENT);
        box.setPrefWidth(CELL_WIDTH);
        box.setStyle(CONTROL_STYLE);
        box.valueProperty().addListener((obs, old, value) -> onChange.run());
        return box;
    }

    private TextField numberField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefWidth((CELL_WIDTH - 10) / 2);
        field.setPrefHeight(32);
        field.setStyle("-fx-border-color: gray; -fx-background-color: rgba(255,255,255,0.7); " + CONTROL_STYLE);
        field.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().matches(FormInput.NUMBER_PATTERN) ? change : null));
        field.textProperty().addListener((obs, old, value) -> onChange.run());
        return field;
    }
}
