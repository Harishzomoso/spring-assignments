package com.example.blinkist.service;

import com.example.blinkist.dto.BookDTO;
import com.example.blinkist.dto.CategoryDTO;
import com.example.blinkist.exception.ResourceNotFoundException;
import com.example.blinkist.model.Book;
import com.example.blinkist.model.Category;
import com.example.blinkist.repository.BookRepository;
import com.example.blinkist.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // DTO mapping methods
    public BookDTO toBookDTO(Book book) {
        if (book == null) return null;
        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getSummary(),
            toCategoryDTO(book.getCategory())
        );
    }

    public Book toBookEntity(BookDTO dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setSummary(dto.getSummary());
        if (dto.getCategory() != null && dto.getCategory().getId() != null) {
            Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategory().getId()));
            book.setCategory(category);
        } else {
            book.setCategory(null);
        }
        return book;
    }

    public CategoryDTO toCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getId(), category.getName());
    }

    public Category toCategoryEntity(CategoryDTO dto) {
        if (dto == null) return null;
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}