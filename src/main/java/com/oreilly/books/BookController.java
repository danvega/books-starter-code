package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.get(id);
        if( book == null ) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Book book) throws BookAlreadyExistsException {
        if(bookService.exists(book)) {
            throw new BookAlreadyExistsException(book);
        } else {
            bookService.create(book);
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Book book, @PathVariable int id) {
        bookService.update(book,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        bookService.delete(id);
    }
}
