package com.redis.sprinboot.service;

import com.redis.sprinboot.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
    String deleteBook(Long id);
}
