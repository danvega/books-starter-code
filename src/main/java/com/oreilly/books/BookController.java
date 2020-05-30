package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public void create(@RequestBody Map<String, String> payload) {
        books.add(payload.get("title"));
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> payload){
        if( books.contains(payload.get("oldTitle")) ) {
            books.set(books.indexOf(payload.get("oldTitle")),payload.get("newTitle"));
        }
    }

    @DeleteMapping
    public void delete(@RequestParam String title){
        if(books.contains(title)){
            books.remove(books.indexOf(title));
        }
    }
}
