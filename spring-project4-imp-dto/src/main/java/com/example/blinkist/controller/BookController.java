package com.example.blinkist.controller;

import com.example.blinkist.dto.BookDTO;
import com.example.blinkist.model.Book;
import com.example.blinkist.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAllBooks().stream()
                .map(bookService::toBookDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book saved = bookService.saveBook(bookService.toBookEntity(bookDTO));
        return new ResponseEntity<>(bookService.toBookDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(bookService.toBookDTO(book));
    }

    @GetMapping("/search/{author}")
    public List<BookDTO> searchByAuthor(@PathVariable String author) {
        return bookService.searchByAuthor(author).stream()
                .map(bookService::toBookDTO)
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book updated = bookService.updateBook(id, bookService.toBookEntity(bookDTO));
        return ResponseEntity.ok(bookService.toBookDTO(updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
         bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}