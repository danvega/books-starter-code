package com.oreilly.books;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books;

    public BookService() {
        books = new ArrayList<>();
        books.add(new Book(1,"Hacking with Spring Boot 2.3","Greg L. Turnquist"));
        books.add(new Book(2,"97 Things Every Java Programmer Should Know", "Kevlin Henney and Trisha Gee"));
        books.add(new Book(3,"Spring Boot: Up and Running","Greg L. Turnquist "));
    }

    public List<Book> list() {
        return books;
    }

    public Book get(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public void create(Book book) throws BookAlreadyExistsException {
        if( book != null) {
            book.setId(books.size()+1);
            books.add(book);
        }
    }

    public boolean exists(Book book) {
        Book found = books.stream().filter(b -> b.getTitle().toLowerCase().equals(book.getTitle().toLowerCase())).findFirst().orElse(null);
        return found != null;
    }

    public void update(Book book, int id) {
        Book currentBook = books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if(currentBook != null) {
            books.set(books.indexOf(currentBook),book);
        }
    }

    public void delete(int id){
        books.removeIf(book -> book.getId() == id);
    }

}
