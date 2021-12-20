package com.virtualpairprogrammers.isbntools;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService dbService;

    public void setWebService(ExternalISBNDataService service) {
        this.webService = service;
    }

    public void setDbService(ExternalISBNDataService dbService) {
        this.dbService = dbService;
    }

    public String getLocatorCode(String isbn) {
        Book book = dbService.lookup(isbn);
        if (book == null) book = webService.lookup(isbn);
        return isbn.substring(isbn.length() - 4)
                + book.getAuthor().charAt(0)
                + book.getTitle().split(" ").length;
    }
}
