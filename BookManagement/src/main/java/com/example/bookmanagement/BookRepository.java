package com.example.bookmanagement;
//interfejs repo
import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void deleteById(Long id);
}
