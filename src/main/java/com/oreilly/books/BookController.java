package com.oreilly.books;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<String> books;

    public BookController() {
        books = new ArrayList<>();
        books.add("Hacking with Spring Boot 2.3");
        books.add("97 Things Every Java Programmer Should Know");
        books.add("Spring Boot: Up and Running");
    }

    @GetMapping
    public List<String> list() {
        return books;
    }

}
