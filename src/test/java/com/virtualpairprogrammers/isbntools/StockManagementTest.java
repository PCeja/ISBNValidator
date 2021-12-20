package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDBService;
    StockManager stockmanager;

    // Instead of @Before in JUnit4
    @BeforeEach
    public void setup() {
        // Instanciations
        testWebService = mock(ExternalISBNDataService.class);
        testDBService = mock(ExternalISBNDataService.class);
        stockmanager = new StockManager();
        stockmanager.setWebService(testWebService);
        stockmanager.setDbService(testDBService);
    }


    @Test
    public void testCanGetACorrectLocatorCode() {
        // Stubs  used to:
        // Override external dependencies
        // Test data
        testDBService = isbn -> null;
        // Stub(Commented) Vs Mockito
//         ExternalISBNDataService testWebService = isbn ->
//                 new Book(isbn, "Of Mice And Men", "J. Steinbeck");
        when(testWebService.lookup(anyString())).thenReturn(new Book("1040177396", "Of Mice And Men", "J. Steinbeck"));

        stockmanager.setDbService(testDBService);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void dataBaseIsUsedIfDataIsPresent() {
        // Mocks used to
        // Override external dependencies
        // Test Behavior
        when(testDBService.lookup("1040177396")).thenReturn(new Book("1040177396", "abc", "abc"));

        stockmanager.setDbService(testDBService);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(testDBService, atMostOnce()).lookup("1040177396");
        // Verify  webService.lookup never called
        // as the book was found in DB
        verify(testWebService, never());

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentinDB() {
        when(testDBService.lookup("1040177396")).thenReturn(null);
        when(testWebService.lookup("1040177396")).thenReturn(new Book("1040177396", "abc", "abc"));

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(testDBService, atMostOnce()).lookup("1040177396");
        // Verify webService.lookup call once
        // as the book wasn't found in DB
        verify(testWebService).lookup(anyString());
    }
}
