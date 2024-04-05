package com.example.bookmanagement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
//Implementacja Usługi
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id);
        return mapToResponse(book);
    }

    @Override
    public BookResponse createBook(BookCreateRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setYear(bookRequest.getYear());
        bookRepository.save(book);
        return mapToResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookCreateRequest bookRequest) {
        Book existingBook = bookRepository.findById(id);
        if (existingBook == null) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setYear(bookRequest.getYear());
        bookRepository.save(existingBook);
        return mapToResponse(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookResponse mapToResponse(Book book) {
        if (book == null) {
            return null;
        }
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setYear(book.getYear());
        return response;
    }
}
