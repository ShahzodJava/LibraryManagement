package com.agmc.LibraryManagement.service;

import com.agmc.LibraryManagement.exception.BookAlreadyExistsException;
import com.agmc.LibraryManagement.exception.ResourceNotFoundException;
import com.agmc.LibraryManagement.mappers.BookMapper;
import com.agmc.LibraryManagement.model.dto.BookDTO;
import com.agmc.LibraryManagement.model.entity.Book;
import com.agmc.LibraryManagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO saveBook(BookDTO bookDTO) {
        bookRepository.findByIsbn(bookDTO.getIsbn()).ifPresent(existingBook -> {
                    throw new BookAlreadyExistsException("Book already exists");
                }
        );

        Book savedBook = bookRepository.save(bookMapper.mapToEntity(bookDTO));
        return bookMapper.mapToDTO(savedBook);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(
                bookMapper::mapToDTO
        ).toList();
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found with ID: " + id)
        );
        return bookMapper.mapToDTO(book);
    }

}
