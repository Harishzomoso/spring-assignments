package com.example.blinkist.service;

import com.example.blinkist.exception.ResourceNotFoundException;
import com.example.blinkist.model.Book;
import com.example.blinkist.repository.BookRepository;
import com.example.blinkist.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book saveBook(Book book) {
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            book.setCategory(categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        } else {
            book.setCategory(null);
        }
        return repository.save(book);
    }

    public Book getBookById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public List<Book> searchByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setSummary(updatedBook.getSummary());
        existingBook.setCategory(updatedBook.getCategory());
        return repository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        repository.deleteById(id);
    }
}