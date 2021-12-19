package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockManagementTest {

    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalISBNDataService testServiceResponse = isbn -> new Book(isbn, "Of Mice And Men", "J. Steinbeck");

        StockManager stockmanager = new StockManager();
        stockmanager.setServiceResponse(testServiceResponse);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }
}
