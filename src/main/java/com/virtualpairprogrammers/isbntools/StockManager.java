package com.virtualpairprogrammers.isbntools;

public class StockManager {

    private ExternalISBNDataService service;

    public void setServiceResponse(ExternalISBNDataService service) {
        this.service = service;
    }

    public String getLocatorCode(String isbn) {
        Book book = service.lookup(isbn);
        return isbn.substring(isbn.length() - 4) +
                book.getAuthor().charAt(0) +
                book.getTitle().split(" ").length;
    }
}
