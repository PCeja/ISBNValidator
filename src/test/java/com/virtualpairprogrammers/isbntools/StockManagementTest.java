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
        ExternalISBNDataService dbService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(dbService.lookup("1040177396")).thenReturn(new Book("1040177396", "abc", "abc"));

        StockManager stockmanager = new StockManager();
        stockmanager.setWebService(webService);
        stockmanager.setDbService(dbService);

        String isbn = "1040177396";
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(dbService, times(1)).lookup("1040177396");
        // Verify lookup method is not called in webService
        // as the book was found in dbService
        verify(webService, times(0)).lookup(anyString());

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentinDB() {

    }
}
