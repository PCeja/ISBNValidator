package com.virtualpairprogrammers.isbntools;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateISTBTest {

    @Test
    public void validTenDigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("0140449116");

        assertTrue("First value",isValidISBN);
        isValidISBN= validator.checkISBN("0140177396");
        assertTrue("Second value",isValidISBN);
    }

    @Test
    public void validISBNThirteenDigit(){
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("9781853260087");
        assertTrue(isValidISBN);
        isValidISBN= validator.checkISBN("9781853267338");
        assertTrue(isValidISBN);
    }

    @Test
    public void validISBNEndingWithX(){
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("012000030X");
        assertTrue(isValidISBN);
    }

    @Test
    public void invalidLessThanTenDigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("0140449117");

        assertFalse(isValidISBN);
    }

    @Test
    public void invalidThirthteenDigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean isValidISBN = validator.checkISBN("9781853260088");

        assertFalse(isValidISBN);
    }

    @Test(expected = NumberFormatException.class)
    public void checkInvalidNineDigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("014044911");
    }

    @Test(expected = NumberFormatException.class)
    public void checkInvalidLettersISBN(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloWorld");
    }
}
