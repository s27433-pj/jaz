package com.example.bookmanagement;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse getBookById(Long id);
    BookResponse createBook(BookCreateRequest bookRequest);
    BookResponse updateBook(Long id, BookCreateRequest bookRequest);
    void deleteBook(Long id);
}
