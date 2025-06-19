package com.example.blinkist.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String summary;
    private CategoryDTO category;

    public BookDTO() {}

    public BookDTO(Long id, String title, String author, String summary, CategoryDTO category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public CategoryDTO getCategory() { return category; }
    public void setCategory(CategoryDTO category) { this.category = category; }
}
