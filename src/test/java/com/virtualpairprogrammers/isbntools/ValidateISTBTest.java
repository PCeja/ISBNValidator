package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISTBTest {

    @Test
    public void validTenDigitsISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("0140449116");

        assertTrue(isValidISBN, "First value");
        isValidISBN = validator.checkISBN("0140177396");
        assertTrue(isValidISBN, "Second value");
    }

    @Test
    public void validISBNThirteenDigit() {
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("9781853260087");
        assertTrue(isValidISBN);
        isValidISBN = validator.checkISBN("9781853267338");
        assertTrue(isValidISBN);
    }

    @Test
    public void validISBNEndingWithX() {
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("012000030X");
        assertTrue(isValidISBN);
    }

    @Test
    public void invalidLessThanTenDigitsISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("0140449117");

        assertFalse(isValidISBN);
    }

    @Test
    public void invalidThirthteenDigitsISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("9781853260088");

        assertFalse(isValidISBN);
    }

    @Test
    public void checkInvalidNineDigitsISBN() {
        ValidateISBN validator = new ValidateISBN();
        Assertions.assertThrows(NumberFormatException.class, () -> validator.checkISBN("014044911"));
    }

    @Test
    public void checkInvalidLettersISBN() {
        ValidateISBN validator = new ValidateISBN();
        Assertions.assertThrows(NumberFormatException.class, () -> validator.checkISBN("helloWorld"));
    }
}
