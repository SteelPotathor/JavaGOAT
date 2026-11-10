package com.example.javagoat;

import com.example.javagoat.Model.Passion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormInputTest {

    @Test
    void passionChoicesCanAllBeConvertedBack() {
        // The forms used to offer LITTERATURE, PROGRAMMATION and THEATHER: valueOf() threw when one was checked
        for (String name : FormInput.names(Passion.miscellaneous.class)) {
            assertDoesNotThrow(() -> Passion.miscellaneous.valueOf(name));
        }
        assertEquals(Passion.miscellaneous.values().length, FormInput.names(Passion.miscellaneous.class).size());
    }

    @Test
    void personNumbersAreChecked() {
        assertTrue(FormInput.checkPersonNumbers("30", "170", "100").isEmpty());
        assertEquals(3, FormInput.checkPersonNumbers("10", "", "300").size());
    }

    @Test
    void namesWithAccentsAndHyphensAreAccepted() {
        assertTrue("Jean-Pierre".matches(FormInput.NAME_PATTERN));
        assertTrue("Hélène O'Neil".matches(FormInput.NAME_PATTERN));
        assertFalse("R2D2".matches(FormInput.NAME_PATTERN));
    }
}
