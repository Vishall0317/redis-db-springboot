package com.redis.sprinboot.service;

import com.redis.sprinboot.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final String HASH_KEY = "book";

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Book> getAllBooks() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    @Override
    public Book getBookById(Long id) {
        return (Book) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    @Override
    public Book saveBook(Book book) {
        redisTemplate.opsForHash().put(HASH_KEY, book.getId(), book);
        return book;
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (redisTemplate.opsForHash().hasKey(HASH_KEY, id)) {
            book.setId(id);
            redisTemplate.opsForHash().put(HASH_KEY, id, book);
        }
        return null;
    }

    @Override
    public String deleteBook(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "product removed!!";
    }
}

