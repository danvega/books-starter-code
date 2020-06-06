package com.oreilly.books;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books;

    public BookController() {
        books = new ArrayList<>();
        books.add(new Book(1,"Hacking with Spring Boot 2.3","Greg L. Turnquist"));
        books.add(new Book(2,"97 Things Every Java Programmer Should Know", "Kevlin Henney and Trisha Gee"));
        books.add(new Book(3,"Spring Boot: Up and Running","Greg L. Turnquist "));
    }

    @GetMapping
    public List<Book> list() {
        return books;
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public void create(@RequestBody Book book) {
        if( book != null) {
            book.setId(books.size()+1);
            books.add(book);
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Book book, @PathVariable int id) {
        Book currentBook = books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if(currentBook != null) {
            books.set(books.indexOf(currentBook),book);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        books.removeIf(book -> book.getId() == id);
    }
}
