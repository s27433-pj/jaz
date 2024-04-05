package com.example.bookmanagement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryBookRepository implements BookRepository{
    private Map<Long, Book> books = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book findById(Long id) {
        return books.get(id);
    }

    @Override
    public void save(Book book) {
        if (book.getId() == null) {
            book.setId(nextId++);
        }
        books.put(book.getId(), book);
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id);
    }
}
