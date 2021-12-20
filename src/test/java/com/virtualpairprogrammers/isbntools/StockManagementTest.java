package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    @Test
    public void testCanGetACorrectLocatorCode() {
        // Stubs
        ExternalISBNDataService testServiceResponse = isbn -> new Book(isbn, "Of Mice And Men", "J. Steinbeck");
        ExternalISBNDataService testDBService = isbn -> null;

        StockManager stockmanager = new StockManager();
        stockmanager.setWebService(testServiceResponse);
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
        ExternalISBNDataService dbService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(dbService.lookup("1040177396")).thenReturn(new Book("1040177396", "abc", "abc"));

        StockManager stockmanager = new StockManager();
        stockmanager.setWebService(webService);
        stockmanager.setDbService(dbService);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(dbService, atMostOnce()).lookup("1040177396");
        // Verify  webService.lookup never called
        // as the book was found in DB
        verify(webService, never());

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentinDB() {
        ExternalISBNDataService dbService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(dbService.lookup("1040177396")).thenReturn(null);
        when(webService.lookup("1040177396")).thenReturn(new Book("1040177396", "abc", "abc"));

        StockManager stockmanager = new StockManager();
        stockmanager.setWebService(webService);
        stockmanager.setDbService(dbService);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(dbService, atMostOnce()).lookup("1040177396");
        // Verify webService.lookup call once
        // as the book wasn't found in DB
        verify(webService).lookup(anyString());
    }
}
