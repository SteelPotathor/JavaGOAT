package com.example.javagoat;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

/** Input rules shared by the "new profile", "edit profile" and "search" forms. */
final class FormInput {

    /** Letters (accents included), spaces, apostrophes and hyphens: "Jean-Pierre", "O'Neil", "Hélène". */
    static final String NAME_PATTERN = "[\\p{L}' -]*";
    /** At most 3 digits: avoids NumberFormatException on huge numbers. */
    static final String NUMBER_PATTERN = "[0-9]{0,3}";

    static final int MIN_AGE = 18;
    static final int MAX_AGE = 99;
    static final int MIN_SIZE = 100;
    static final int MAX_SIZE = 250;
    static final int MIN_IQ = 40;
    static final int MAX_IQ = 200;

    private FormInput() {
    }

    /** @return the problems found, empty when every value is in its range */
    static List<String> checkProfileNumbers(String age, String size, String iq, String preferredAge, String preferredSize) {
        List<String> problems = new ArrayList<>();
        checkRange(problems, "Age", age, MIN_AGE, MAX_AGE);
        checkRange(problems, "Size (cm)", size, MIN_SIZE, MAX_SIZE);
        checkRange(problems, "IQ", iq, MIN_IQ, MAX_IQ);
        checkRange(problems, "Preferred age", preferredAge, MIN_AGE, MAX_AGE);
        checkRange(problems, "Preferred size (cm)", preferredSize, MIN_SIZE, MAX_SIZE);
        return problems;
    }

    private static void checkRange(List<String> problems, String label, String text, int min, int max) {
        try {
            int value = Integer.parseInt(text.strip());
            if (value < min || value > max) {
                problems.add(label + " must be between " + min + " and " + max + " (got " + value + ")");
            }
        } catch (NumberFormatException e) {
            problems.add(label + " is not a number");
        }
    }

    static void showProblems(String header, List<String> problems) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(header);
        alert.setContentText(String.join("\n", problems));
        alert.showAndWait();
    }
}
