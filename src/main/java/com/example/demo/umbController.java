package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class umbController {
    private List<Book> books;
    public umbController() {
        this.books = init();
    }
    private List<Book> init() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setAuthor("Autor 1");
        book1.setTitle("Nazov 1");
        books.add(book1);
        Book book2 = new Book();
        book2.setAuthor("Autor 2");
        book2.setTitle("Nazov 2");
        books.add(book2);
        return books;
    }
    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String bookAuthor) {
        List<Book> filteredBooks = new ArrayList<>();
        if (bookAuthor == null) {
            return this.books;
        }
        for (Book book : books) {
            if (book.getAuthor().equals(bookAuthor)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
    @GetMapping("/api/book/{bookid}")
    public Book getBook(@PathVariable Integer bookid) {
        return this.books.get(bookid);
    }
    @GetMapping("/api/book")
    public Integer createBook(Book book) {
        this.books.add(book);
        return this.books.size() -1;
    }
    @GetMapping("/api/books/{bookid}")
    public void deleteBook(@PathVariable Integer bookid) {
        this.books.remove(this.books.get(bookid));
    }
    @GetMapping("/api/books/{bookid}")
    public void updateBook(@PathVariable Integer bookid, @RequestBody Book book) {
        this.books.get(bookid).setTitle(book.getTitle());
        this.books.get(bookid).setAuthor(book.getAuthor());
    }
}
